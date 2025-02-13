-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.data-1.0
visibility=public
singleton=true
IBM-ShortName: data-1.0
IBM-API-Package: \
  jakarta.data; type="spec",\
  jakarta.data.exceptions; type="spec",\
  jakarta.data.provider; type="spec",\
  jakarta.data.repository; type="spec"
Subsystem-Name: Jakarta Data 1.0
#TODO com.ibm.websphere.appserver.eeCompatible-11.0
#TODO io.openliberty.jakartaeePlatform-11.0
-features=\
  com.ibm.websphere.appserver.eeCompatible-10.0,\
  io.openliberty.cdi-4.0,\
  io.openliberty.jakarta.data-1.0
-bundles=\
  io.openliberty.data.internal
kind=beta
edition=base
WLP-Activation-Type: parallel
