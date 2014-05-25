# Samples - CDI - Alternatives - Datasources

This sample shows how to use CDI alternatives to switch datasources (H2 in test environment, Derby in development and Postgres in production)

More on my blog

## Configuring JBoss (Wildfly 8)

First of all, you need to add Derby and Postgres JDBC drivers into JBoss under the `$JBOSS_HOME/modules/system/layers/base` directory.
* `org/apache/derby` for the Derby JDBC driver module
* `org/postgresql` for the Postgres JDBC driver module

Then, execute the CLI `src/main/script/jboss-setup.cli` script to create the three datasources.

# Licensing

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.

<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>