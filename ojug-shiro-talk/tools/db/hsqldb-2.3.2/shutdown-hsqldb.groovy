import groovy.sql.Sql

def sql = Sql.newInstance( 'jdbc:hsqldb:hsql://localhost/ojug-shirotalk', 'sa', '', 'org.hsqldb.jdbcDriver' )
sql.execute("shutdown");