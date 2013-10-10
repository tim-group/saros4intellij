#! /bin/sh -x

filename=/src/com/timgroup/alice/D.java
offset=0
sarosserver=http://localhost:7373/saros

wget -S --header="Content-Type: application/json" --post-data "{ \"filename\": \"${filename}\", \"offset\": ${offset} }" ${sarosserver}/location
