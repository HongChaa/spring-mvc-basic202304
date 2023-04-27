package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

import static com.spring.mvc.chap04.entity.Grade.A;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Repository("jdbc") // 스프링 빈 등록 : 객체의 생성의 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository {

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String userName= "root";
    private String password = "1234";

    // key: 학번, value: 성적정보
    // private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호
//    private static int sequence;

//    static {
//        scoreMap = new HashMap<>();
//        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 34, 91));
//        Score stu2 = new Score(new ScoreRequestDTO("춘식이", 77, 99, 87));
//        Score stu3 = new Score(new ScoreRequestDTO("대길이", 98, 66, 85));
//
//        stu1.setStuNum(++sequence);
//        stu2.setStuNum(++sequence);
//        stu3.setStuNum(++sequence);
//
//        scoreMap.put(stu1.getStuNum(), stu1);
//        scoreMap.put(stu2.getStuNum(), stu2);
//        scoreMap.put(stu3.getStuNum(), stu3);
//    }

    @Override
    public List<Score> findAll() {
//        return scoreMap.values()
//                .stream()
//                .sorted(comparing(Score::getStuNum))
//                .collect(toList())
//                ;
        List<Score> scoreList = new ArrayList<>();
        ResultSet rs;
        Score score = null;
        ScoreRequestDTO dto = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(false);

            String sql = "SELECT * FROM STUDENT";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next()) {
                dto = new ScoreRequestDTO();
                dto.setName(rs.getString("NAME"));
                dto.setKor(rs.getInt("KOR"));
                dto.setEng(rs.getInt("ENG"));
                dto.setMath(rs.getInt("MATH"));
                score = new Score(dto);
                score.setStuNum(rs.getInt("STU_NUM"));

                scoreList.add(score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparator = comparing(Score::getStuNum);
        switch (sort) {
            case "num":
                comparator = comparing(Score::getStuNum);
                break;
            case "name":
                comparator = comparing(Score::getName);
                break;
            case "avg":
                comparator = comparing(Score::getAverage).reversed();
                break;
        }
        return findAll()
                .stream()
                .sorted(comparator)
                .collect(toList())
                ;
    }

    @Override
    public boolean save(Score score) {
//        if (scoreMap.containsKey(score.getStuNum())) {
//            return false;
//        }
//        score.setStuNum(++sequence);
//        scoreMap.put(score.getStuNum(), score);
//        System.out.println(findAll());

        Connection conn = null;
        int n = 0;

        try {
            conn = DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "INSERT INTO STUDENT (NAME, KOR, ENG, MATH, TOTAL, AVERAGE, GRADE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setFloat(6, (float)score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            n = pstmt.executeUpdate();
            if(n > 0) conn.commit();
            else conn.rollback();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                 e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(n > 0) return true;
        else return false;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
//        if (!scoreMap.containsKey(stuNum)) return false;
//        scoreMap.remove(stuNum);
//        return true;
        Connection conn = null;
        int n = 0;

        try {
            conn = DriverManager.getConnection(url, userName, password);

            conn.setAutoCommit(false);

            String sql = "DELETE FROM STUDENT WHERE STU_NUM = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            n = pstmt.executeUpdate();
            if(n > 0) conn.commit();
            else conn.rollback();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(n > 0) return true;
        else return false;

    }

    @Override
    public Score findByStuNum(int stuNum) {
        ResultSet rs;
        Score score = null;
        ScoreRequestDTO dto = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(false);

            String sql = "SELECT * FROM STUDENT WHERE STU_NUM = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                dto = new ScoreRequestDTO();
                dto.setName(rs.getString("NAME"));
                dto.setKor(rs.getInt("KOR"));
                dto.setEng(rs.getInt("ENG"));
                dto.setMath(rs.getInt("MATH"));
                score = new Score(dto);
                score.setStuNum(rs.getInt("STU_NUM"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return scoreMap.get(stuNum);
        return score;
    }


    public int updateScore(Score score) {
        Connection conn = null;
        int result = 0;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(false);

            String sql = "UPDATE STUDENT SET KOR=?, ENG=?, MATH=? WHERE STU_NUM=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, score.getKor());
            pstmt.setInt(2, score.getEng());
            pstmt.setInt(3, score.getMath());
            pstmt.setInt(4, score.getStuNum());


            result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }



}
