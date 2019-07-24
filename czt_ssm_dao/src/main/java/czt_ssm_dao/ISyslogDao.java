package czt_ssm_dao;

import czt_ssm_domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISyslogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(Syslog syslog) ;


    @Select("select * from syslog" )
    @Results({
            @Result(id = true ,property = "id",column = "id"),
            @Result(property = "visitTime",column = "visitTime"),
            @Result(property = "visitTimeStr",column = "visitTimeStr"),
            @Result(property = "username",column = "username"),
            @Result(property = "ip",column = "ip"),
            @Result(property = "url",column = "url"),
            @Result(property = "executionTime",column = "executionTime")
    })
    List<Syslog> findAll();
}
