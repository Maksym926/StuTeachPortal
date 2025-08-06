package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.AddNewTopicInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseContentInterface;
import com.EduTech.educationportal.interfaces.view.SetupControllerInterface;
import com.EduTech.educationportal.model.Course;
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

public class ManageCourseContentController implements ManageCourseContentInterface, SetupControllerInterface {

    Course course;
    ManageCourseContentPresenter presenter;

    @FXML
    private TreeView<String> courseContentList;

    public ManageCourseContentController(Course course){
        this.course = course;
        Log.info("The course was parsed "  + course.getID());
    }

    @Override
    public void setup() {
        TreeItem<String> rootItem = new TreeItem<>("Topics");
        rootItem.setExpanded(true);

        TreeItem<String> topic1 = new TreeItem<>("Objects");
        topic1.getChildren().addAll(
                new TreeItem<>("Inheritance"),
                new TreeItem<>("Polymorphism")
        );
        TreeItem<String> topic2 = new TreeItem<>("Algorithms");
        topic2.getChildren().addAll(
                new TreeItem<>("Binary search"),
                new TreeItem<>("Depth search")
        );
        rootItem.getChildren().addAll(topic1, topic2);
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
