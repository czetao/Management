package czt_ssm_service.serviceImpl;

import czt_ssm_dao.ISyslogDao;
import czt_ssm_domain.Syslog;
import czt_ssm_service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISyslogServiceImpl implements ISyslogService {

    @Autowired
    private ISyslogDao iSyslogDao;

    @Override
    public void save(Syslog syslog) {
        iSyslogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll() {
        return iSyslogDao.findAll();

    }
}
