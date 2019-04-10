# Cloud Pipeline

### Building

```sh
sbt assembly
```

#### Bigtable setup

Install cbt

```sh
gcloud components update
gcloud components install cbt
```

Configure cbt
```sh
cat <<EOF> ~/.cbtrc
project = myproject
instance = myinstance
EOF
```

```sh
cbt deletetable metrics
cbt ls
cbt createtable metrics families=metrics
cbt ls
cbt count metrics
```

If table already exists:

```sh
cbt createfamily metrics metrics
```


### Testing

```sh
java -Xms1g -Xmx1g -cp target/scala-2.11/CloudPipeline.jar com.google.cloud.example.CloudPipeline --project=myproject --subscription=mysub --instanceId=metrics --tableId=metrics --columnFamily=metrics --column=metrics --serviceAccount=metrics@myproject.iam.gserviceaccount.com --subnetwork=regions/us-central1/subnetworks/default --usePublicIps=false --runner=DataflowRunner --stagingLocation=gs://bucket/dataflow/staging/ --tempLocation=gs://bucket/dataflow/temp/

java -cp target/scala-2.11/CloudPipeline.jar com.google.cloud.example.CloudPublish --project=myproject --topic=mysub

java -cp target/scala-2.11/CloudPipeline.jar com.google.cloud.example.QueueDepth --project=myproject --subscription=mysub

java -cp target/scala-2.11/CloudPipeline.jar com.google.cloud.example.CloudServlet -p myproject -i metrics -t metrics

curl "http://localhost:8080/top?host=h127&dc=dc3&region=r1"
curl "http://localhost:8080/metrics?host=h127&dc=dc3&region=r1&limit=3"
```
