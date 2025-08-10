package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddNewTopicInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseContentInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewTopicPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCourseContentPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCoursePresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;
import java.util.List;

public class ManageCourseContentController implements ManageCourseContentInterface, SetupControllerInterface {

    Course course;
    ManageCourseContentPresenter presenter;

    List<Topic> topics = new ArrayList<>();

    @FXML
    private TreeView<String> courseContentList;

    public ManageCourseContentController(Course course){
        this.course = course;
        Log.info("The course was parsed "  + course.getID());
    }

    @Override
    public void setup() {
        presenter.getTopicByCourseID(course.getID(), topics);


        TreeItem<String> rootItem = new TreeItem<>("Topics");
        rootItem.setExpanded(true);
        for (Topic topic : topics){
            rootItem.getChildren().add(new TreeItem<>(topic.getTitle()));
        }




        courseContentList.setRoot(rootItem);


    }
    @FXML
    public void openAddTopicWindow(ActionEvent event){
        AddNewTopicInterface addNewTopicInterface = new AddNewTopicController(course);
        CourseContentRepositoryInterface courseContentRepositoryInterface = new CourseContentRepository();
        AddNewTopicPresenter addNewTopicPresenter = new AddNewTopicPresenter(addNewTopicInterface, courseContentRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/AddNewTopicView.fxml", "Add new Topic", addNewTopicInterface, true);
    }

    @Override
    public void setPresenter(ManageCourseContentPresenter presenter) {
        this.presenter = presenter;
    }
}
