#!/bin/bash

clearDB () {
  psql postgres://lwhzzfglqzmohj:caf222c6ec61a394ac34989679eb98c5d432a664e68c5cf3c319199318f74954@ec2-44-198-154-255.compute-1.amazonaws.com:5432/ddsk1hvat3st1k << EOF
TRUNCATE address,book,customer,head_librarian,item_instance,librarian,library,library_card,library_hour,loan,movie,music,newspaper,online_account,person,reservation,shift
EOF
}


NPM=$( npm | grep "command not found")
echo "$NPM"
if [[ -n $NPM ]]; then
    echo "Cannot run integration tests without npm"
    exit 1
fi

NEWMAN=$(newman | grep "command not found")
if [[ -n $NEWMAN ]]; then
    printf "Cannot run integration tests without newman installed\n Run npm install -g newman to install"
    exit 1
fi

path="$(pwd)/$(dirname  $0 )"
clearDB

collection=$(find "$path" -name "*environment.json")
for file in $path/*collection.json
do
  echo "Executing test $file"
  newman run "$file" -e "$collection" --bail
  clearDB
done

