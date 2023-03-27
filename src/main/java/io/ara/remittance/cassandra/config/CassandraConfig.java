package io.ara.remittance.cassandra.config;

import io.ara.remittance.cassandra.data.Student;
import io.ara.remittance.cassandra.service.CassandraServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraTemplateFactoryBean;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

import java.util.List;
import java.util.UUID;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "cass";
    }

    @Bean(name = "CassandraConnectorConstants.LOGGER_NAME")
    public Logger loggerBean() {
        return LoggerFactory.getLogger("CassandraConnectorConstants.LOGGER_NAME");
    }
//
//    @Bean
//    public CassandraSessionProvider cassandraSessionProvider(@Qualifier(CassandraConnectorConstants.LOGGER_NAME) final Logger logger) {
//        final CassandraSessionProvider cassandraSessionProvider = new CassandraSessionProvider(this.env, logger);
//        cassandraSessionProvider.setAdminClusterName(
//                this.env.getProperty(CassandraConnectorConstants.CLUSTER_NAME_PROP, CassandraConnectorConstants.CLUSTER_NAME_PROP_DEFAULT));
//        cassandraSessionProvider.setAdminContactPoints(
//                this.env.getProperty(CassandraConnectorConstants.CONTACT_POINTS_PROP, CassandraConnectorConstants.CONTACT_POINTS_PROP_DEFAULT));
//        cassandraSessionProvider.setAdminKeyspace(
//                this.env.getProperty(CassandraConnectorConstants.KEYSPACE_PROP, CassandraConnectorConstants.KEYSPACE_PROP_DEFAULT));
//
//        cassandraSessionProvider.touchAdminSession();
//
//        return cassandraSessionProvider;
//    }
//    @Bean
//    public CassandraServiceInterface serviceInterface(){
//        return new CassandraServiceInterface() {
//            @Override
//            public String createStudent(Student student) {
//                return null;
//            }
//
//            @Override
//            public Student getStudentById(UUID id) {
//                return null;
//            }
//
//            @Override
//            public Student getStudentByEmail(String email) {
//                return null;
//            }
//
//            @Override
//            public List<Student> getStudentByGroup(String email) {
//                return null;
//            }
//
//            @Override
//            public List<Student> getAllStudents() {
//                return null;
//            }
//
//            @Override
//            public List<Student> getAllStudentsByStatus(boolean status) {
//                return null;
//            }
//
//            @Override
//            public Student updateStudent(UUID id, Student student) {
//                return null;
//            }
//
//            @Override
//            public boolean existById(UUID id) {
//                return false;
//            }
//        };
//    }

//    @Bean
//    public CassandraMappingContext cassandraMapping()
//            throws ClassNotFoundException {
//        return new BasicCassandraMappingContext();
//    }
}