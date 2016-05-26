# dbmi-inmemory-query

### Apache Geode
You could refer to http://geode.docs.pivotal.io/docs/rest_apps/setup_config.html for specific meaning of the configuration

You could refer to http://geode.docs.pivotal.io/docs/getting_started/15_minute_quickstart_gfsh.html for normal commands of gfsh
Start the gfsh and locator:

```sh
$ gfsh
gfsh>start locator --name=locator1
```
Configure locator as follows
```sh
gfsh>configure pdx --read-serialized=true
gfsh>configure pdx --disk-store
```
Start the server
```sh
gfsh>start server --name=server1 --J=-Dgemfire.start-dev-rest-api=true \
--J=-Dgemfire.http-service-port=8080 --J=-Dgemfire.http-service-bind-address=localhost
```
Create a replicated, persistent region:
```sh
create region --name=regionA --type=REPLICATE_PERSISTENT
```

### Restful Application
Write your Java program, you could refer to this sample code and sample operation: http://geode.docs.pivotal.io/docs/rest_apps/setup_config.html

Deploy application
```sh
#to make your restful application, you need to deploy jar one the server
$ gfsh>deploy --dir=libs/group1-libs
```
