-injar 'inputjar'
-outjar 'outputjar'
# all need lib
-libraryjars 'javahome/lib/rt.jar'
-libraryjars 'mavendir/javax/activation/activation/1.1/activation-1.1.jar'
-libraryjars 'mavendir/aopalliance/aopalliance/1.0/aopalliance-1.0.jar'
-libraryjars 'mavendir/commons-codec/commons-codec/1.6/commons-codec-1.6.jar'
-libraryjars 'mavendir/commons-lang/commons-lang/2.1/commons-lang-2.1.jar'
-libraryjars 'mavendir/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar'
-libraryjars 'mavendir/com/conlect/oatos/dto/1.0/dto-1.0.jar'
-libraryjars 'mavendir/org/apache/httpcomponents/httpclient/4.2.1/httpclient-4.2.1.jar'
-libraryjars 'mavendir/org/apache/httpcomponents/httpcore/4.2.1/httpcore-4.2.1.jar'
-libraryjars 'mavendir/org/apache/httpcomponents/httpmime/4.2.1/httpmime-4.2.1.jar'
-libraryjars 'mavendir/org/codehaus/jackson/jackson-core-asl/1.9.7/jackson-core-asl-1.9.7.jar'
-libraryjars 'mavendir/org/codehaus/jackson/jackson-mapper-asl/1.9.7/jackson-mapper-asl-1.9.7.jar'
-libraryjars 'mavendir/log4j/log4j/1.2.16/log4j-1.2.16.jar'
-libraryjars 'mavendir/javax/mail/mail/1.4.5/mail-1.4.5.jar'
-libraryjars 'mavendir/org/mybatis/mybatis/3.2.0/mybatis-3.2.0.jar'
-libraryjars 'mavendir/org/mybatis/mybatis-spring/1.2.0/mybatis-spring-1.2.0.jar'
-libraryjars 'mavendir/mysql/mysql-connector-java/5.1.25/mysql-connector-java-5.1.25.jar'
-libraryjars 'mavendir/com/google/code/simple-spring-memcached/spymemcached/2.8.4/spymemcached-2.8.4.jar'

-libraryjars 'mavendir/org/springframework/spring-aop/3.2.1.RELEASE/spring-aop-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-beans/3.2.1.RELEASE/spring-beans-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-context/3.2.1.RELEASE/spring-context-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-core/3.2.1.RELEASE/spring-core-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-expression/3.2.1.RELEASE/spring-expression-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-jdbc/3.2.1.RELEASE/spring-jdbc-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-tx/3.2.1.RELEASE/spring-tx-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-web/3.2.1.RELEASE/spring-web-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/org/springframework/spring-webmvc/3.2.1.RELEASE/spring-webmvc-3.2.1.RELEASE.jar'
-libraryjars 'mavendir/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar'

-ignorewarnings
-dontshrink
-dontoptimize
-printmapping 'print.mapping.dir'
-keeppackagenames com.qycloud.oatos.server,com.qycloud.oatos.server.dao,com.qycloud.oatos.server.domain.entity,com.qycloud.oatos.server.util
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod
-keepparameternames
-adaptresourcefilenames **.properties,**.xml
-adaptresourcefilecontents **.properties,META-INF/MANIFEST.MF,**.xml

-keep,allowshrinking class com.qycloud.oatos.server.dao.* {
    <fields>;
    <methods>;
}
-keep,allowshrinking class com.qycloud.oatos.server.domain.entity.* {
    <fields>;
    <methods>;
}
-keep,allowshrinking class com.qycloud.oatos.server.domain.model.* {
}

-keep,allowshrinking class com.qycloud.oatos.server.logic.task.* {
    <methods>;
}

-keep,allowshrinking class com.qycloud.oatos.server.util.UserTokenFilter {
    <methods>;
}
-keep,allowshrinking class com.qycloud.oatos.server.security.SecurityPropertyConfigurer {
    <methods>;
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Also keep - Serialization code. Keep all fields and methods that are used for
# serialization.
-keepclassmembers class * extends java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep names - Native method names. Keep all native class/method names.
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}
