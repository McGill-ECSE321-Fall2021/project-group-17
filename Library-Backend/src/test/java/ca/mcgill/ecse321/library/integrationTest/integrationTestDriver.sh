#!/bin/bash
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

for file in $path/*.json
do
  echo "Executing test $file"
  newman run "$file"
done