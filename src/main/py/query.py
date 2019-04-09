#!/usr/bin/env python

# Copyright 2016 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""
REST API with Bigtable storage
"""


from google.cloud import bigtable
from google.cloud.bigtable import row_filters
from google.cloud.bigtable import row_set
from proto.instancemetric_pb2 import Metrics
from google.protobuf.json_format import MessageToDict
import json
import time
from flask import Flask
from flask import request
from flask import Response


def rowkey(host, dc, region, t):
    if t is not None:
        return "".join([host, "#", dc, "#", region, "#", str(t)])
    else:
        return "".join([host, "#", dc, "#", region, "$"])


class QueryHandler(object):
    def __init__(self, project, instance_id, table_id):
        self.project = project
        self.instance_id = instance_id
        self.table_id = table_id
        self.client = bigtable.Client(project=self.project, admin=False)
        self.instance = self.client.instance(self.instance_id)
        self.table = self.instance.table(self.table_id)

    def query(self, host, dc, region, t, limit=1, window=60):
        start_key = rowkey(host, dc, region, t-window)
        end_key = rowkey(host, dc, region, t)
        return self.table.read_rows(
            limit=limit,
            filter_=row_filters.CellsColumnLimitFilter(1),
            row_set=row_set.RowSet().add_row_range_from_keys(start_key=start_key, end_key=end_key)
        )


app = Flask('MetricsApp')


@app.before_first_request
def init():
    import os
    project = os.environ.get('PROJECT', 'myproject')
    instance_id = os.environ.get('INSTANCE', 'metrics')
    table_id = os.environ.get('TABLE', 'metrics')
    app.QUERY_HANDLER = QueryHandler(project, instance_id, table_id)


def run(host, dc, region, limit):
    t = time.time()
    rows = app.QUERY_HANDLER.query(host, dc, region, t, limit)
    a = []
    for row in rows:
        for cf in row.cells:
            cell = row.cells[cf]
            for col in cell:
                for x in cell[col]:
                    m = Metrics()
                    m.ParseFromString(x.value)
                    a.append(m)
    return a


@app.route('/metrics')
def metrics():
    host = request.args.get('host')
    dc = request.args.get('dc')
    region = request.args.get('region')
    limit = request.args.get('limit')

    if limit is None:
        limit = 1
    else:
        limit = int(limit)

    rows = run(host, dc, region, limit)

    a = []
    for row in rows:
        d = MessageToDict(row, including_default_value_fields=True, preserving_proto_field_name=True)
        a.append(json.dumps(d))

    return Response(response='[' + ",".join(a) + ']',
                    status=200,
                    mimetype='application/json')


if __name__ == '__main__':
    pass