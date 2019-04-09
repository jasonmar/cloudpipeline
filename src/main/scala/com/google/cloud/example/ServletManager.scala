package com.google.cloud.example

import javax.servlet.http.HttpServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

object ServletManager {
  case class App(path: String, servlet: HttpServlet)

  def start(port: Int, apps: Seq[App], initParameters: Seq[(String,String)] = Nil): Server = {
    val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")

    initParameters.foreach{case (name, value) => context.setInitParameter(name, value)}

    apps.foreach{app =>
      val pathSpec = (app.path + "/*").replaceAll("//", "/")
      context.addServlet(new ServletHolder(app.servlet), pathSpec)
    }

    val server = new Server(port)
    val handlers = new HandlerList
    handlers.setHandlers(Array(context))
    server.setHandler(handlers)
    server.start()
    server
  }
}
