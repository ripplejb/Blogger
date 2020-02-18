#!/usr/bin/env sh

cd ./client
ng build
cd ../
cp -a ./client/dist/client/. ./src/main/resources/public/