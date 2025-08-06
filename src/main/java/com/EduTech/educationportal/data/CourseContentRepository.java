package com.EduTech.educationportal.data;

import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
