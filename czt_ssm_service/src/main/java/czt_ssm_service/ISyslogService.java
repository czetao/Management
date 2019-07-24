package czt_ssm_service;

import czt_ssm_domain.Syslog;

import java.util.List;

public interface ISyslogService {

     void save(Syslog syslog);

    List<Syslog> findAll();
}
