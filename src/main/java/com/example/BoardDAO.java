package com.example;

import com.example.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.BoardRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDAO {
    @Autowired
    private JdbcTemplate template ;
    public int insertBoard(BoardVO vo){
        String sql = "insert into BOARD(title, writer, content, category) values ("
                +"'"+vo.getTitle()+"',"
                +"'"+vo.getWriter()+"',"
                +"'"+vo.getContent()+"',"
                +"'"+vo.getCategory()+"')";
        return template.update(sql);
    }
    public int deleteBoard(int seq){
        String spl = "delete from BOARD where seq = " + seq;
        return template.update(spl);
    }
    public int updateBoard(BoardVO vo){
        String sql = "update BOARD set title='" + vo.getTitle()+"',"
                +"writer='"+vo.getWriter() + "',"
                +"content='"+vo.getContent() + "',"
                +"category='"+vo.getCategory() + "'where seq="+vo.getSeq();
        return template.update(sql);
    }
    public BoardVO getBoard(int seq){
        String sql = "select * from BOARD where seq=" +seq;
        return template.queryForObject(sql,new BoardRowMapper());
    }
    public List<BoardVO> getBoardList(){
        String sql = "select * from BOARD order by regdate desc";
        return template.query(sql,new BoardRowMapper());
    }
}
