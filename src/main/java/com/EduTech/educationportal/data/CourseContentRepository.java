package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.SubTopic;
import com.EduTech.educationportal.model.entities.Topic;
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
    public void updateExistingTopic(Topic topic ){
        Log.info("Starting updating course");
        String sql = "UPDATE topicsDB SET title = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,topic.getTitle());
            stmt.setInt(2, topic.getID());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateExistingSubTopic(SubTopic subTopic ){
        Log.info("Starting updating course");
        String sql = "UPDATE subTopicsDB SET title = ?, content = ?, assignment = ?, fileName = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,subTopic.getTitle());
            stmt.setString(2,subTopic.getContent());
            stmt.setString(3,subTopic.getFilePath());
            stmt.setString(4,subTopic.getFileName());
            stmt.setInt(5, subTopic.getID());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getTopicByCourseID(int ID, List<Topic> topics){
        String sql = "SELECT * FROM topicsDB WHERE courseID= ? ";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int topicID = rs.getInt("Id");
                int courseID = rs.getInt("courseID");
                String title = rs.getString("title");
                Topic topic = new Topic(topicID, courseID, title);
                topics.add(topic);

            }
            Log.info("Topics were parsed successfully");

        }catch (SQLException e){
            Log.error("Error while trying to get topics by course ID");
            e.printStackTrace();
        }
    }
    public void getSubTopicByTopicID(int topicID, List<SubTopic> subTopics){
        String sql = "SELECT * FROM subTopicsDB WHERE topicID = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, topicID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                String assignment = rs.getString("assignment");
                String fileName = rs.getString("fileName");
                subTopics.add(new SubTopic(id, topicID, title,content, image, assignment, fileName));
                Log.info("subTopic was successfully received");
            }
        }catch (SQLException e){
            Log.error("Error during getting subTopics from DB");
            e.printStackTrace();
        }
    }
    public void addNewSubTopic(SubTopic subTopic){
        String sql = "INSERT INTO subTopicsDB (topicID, title, content, image, assignment, fileName) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, subTopic.getTopicID());
            stmt.setString(2, subTopic.getTitle());
            stmt.setString(3, subTopic.getContent());
            stmt.setString(4, subTopic.getImage());
            stmt.setString(5, subTopic.getFilePath());
            stmt.setString(6, subTopic.getFileName());
            stmt.executeUpdate();
            Log.info("subTopic was successfully inserted");
        }catch (SQLException e){
            Log.error("Error while adding new sub topic");
            e.printStackTrace();
        }

    }
    public void deleteTopic(int ID){
        String sql = "DELETE FROM topicsDB WHERE id=?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, ID);
            stmt.executeUpdate();
            Log.info("Topic was successfully deleted");

        }catch(SQLException e){
            Log.error("Error while deleting the topic");
            e.printStackTrace();
        }
    }
    public void deleteSubTopic(int ID){
        String sql = "DELETE FROM subTopicsDB WHERE id=?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, ID);
            stmt.executeUpdate();
            Log.info("Topic was successfully deleted");

        }catch(SQLException e){
            Log.error("Error while deleting the topic");
            e.printStackTrace();
        }
    }
    public void printTopicInfo(){
        String sql = "SELECT * FROM subTopicsDB";
        Log.info("Getting topic info from db");
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int ID = rs.getInt("Id");
                int courseID = rs.getInt("topicID");
                String title = rs.getString("title");

                Log.info("printing topic.... " + "ID: " + ID + " " + title + "CourseID" + courseID);
            }

        }catch (SQLException e){
            Log.error("Error while getting course info from db");
            e.printStackTrace();
        }
    }
}