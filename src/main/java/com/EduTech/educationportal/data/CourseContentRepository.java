package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseContentRepository implements CourseContentRepositoryInterface {
    public void addNewTopic(Topic topic){
        String sql = "INSERT INTO topicsDB (courseID, title) Values(?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, topic.getCourseID());
            stmt.setString(2, topic.getTitle());
            stmt.executeUpdate();
            Log.info("Topic was successfully inserted");

        }catch (SQLException e){
            e.printStackTrace();
            Log.info("Error while inserting new topics");
        }
    }

    public void getTopicByCourseID(int ID, List<Topic> topics){
        String sql = "SELECT * FROM topicsDB WHERE courseID= ? ";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int TopicID = rs.getInt("Id");
                int courseID = rs.getInt("courseID");
                String title = rs.getString("title");
                Topic topic = new Topic(title);
                topics.add(topic);

            }
            Log.info("Topics were parsed successfully");

        }catch (SQLException e){
            Log.error("Error while trying to get topics by course ID");
            e.printStackTrace();
        }
    }
    public void printTopicInfo(){
        String sql = "SELECT * FROM topicsDB";
        Log.info("Getting topic info from db");
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int ID = rs.getInt("Id");
                int courseID = rs.getInt("courseID");
                String title = rs.getString("title");

                Log.info("printing topic.... " + "ID: " + ID + " " + title + "CourseID" + courseID);
            }

        }catch (SQLException e){
            Log.error("Error while getting course info from db");
            e.printStackTrace();
        }
    }
}