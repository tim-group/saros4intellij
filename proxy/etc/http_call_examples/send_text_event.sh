#! /bin/sh -x

filename=/src/com/timgroup/alice/D.java
offset=29
inserted="im in ur eclipse"
replaced="public class D"
sarosserver=http://localhost:7373/saros

wget -S --header="Content-Type: application/json" --post-data \
"{ \"filename\": \"${filename}\", \
     \"offset\": ${offset}, \
     \"inserted\": \"${inserted}\", \
     \"replaced\": \"${replaced}\" }" ${sarosserver}/edit
