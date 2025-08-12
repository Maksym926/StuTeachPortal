package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.interfaces.presenter.AddNewSubTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.AddNewTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.*;
import com.EduTech.educationportal.model.Course;
import com.EduTech.educationportal.model.SubTopic;
import com.EduTech.educationportal.model.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewSubTopicPresenter;
import com.EduTech.educationportal.presenter.shared.AddNewTopicPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCourseContentPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCoursePresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;
import java.util.List;

public class ManageCourseContentController implements ManageCourseContentInterface, SetupControllerInterface {

    Course course;
    ManageCourseContentPresenter presenter;

    List<Topic> topics = new ArrayList<>();

    List<SubTopic> subTopics = new ArrayList<>();

    @FXML
    private TreeView<CourseContentItem > courseContentList;

    private TreeItem<CourseContentItem > rootItem;

    public ManageCourseContentController(Course course){
        this.course = course;
        Log.info("The course was parsed "  + course.getID());
    }
    private void setupTree() {
        rootItem = new TreeItem<>(new Topic("Topics")); // root topic
        rootItem.setExpanded(true);
        courseContentList.setRoot(rootItem);

        courseContentList.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(CourseContentItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle(null);
                    setOnMouseClicked(null);
                    return;

                }
                String title = item.getTitle();

                setText(title);
                setStyle(null);
                setOnMouseClicked(null);

                if (title.equals("[Add new topic]")) {
                    setStyle("-fx-text-fill: blue; -fx-underline: true;");
                    setOnMouseClicked(e -> {
                        if (e.getClickCount() == 1) {
                            openAddTopicWindow(new ActionEvent(e.getSource(), null));
                        }
                    });
                } else if (title.equals("[Add new subtopic]")) {
                    setStyle("-fx-text-fill: green; -fx-underline: true;");
                    setOnMouseClicked(e -> {
                        if (e.getClickCount() == 1) {
                            TreeItem<CourseContentItem> parentItem = getTreeItem().getParent();
                            if(parentItem != null){
                                Topic topic = (Topic)parentItem.getValue();
                                openAddSubTopicWindow(new ActionEvent(e.getSource(),null), topic);
                            }

                        }
                    });
                }




            }
        });
    }

    @Override
    public void setup() {
        setupTree();
        presenter.getTopicByCourseID(course.getID(), topics);

        addTopics();
















    }
    @FXML
    public void openAddTopicWindow(ActionEvent event){
        AddNewTopicInterface addNewTopicInterface = new AddNewTopicController(course);
        CourseContentRepositoryInterface courseContentRepositoryInterface = new CourseContentRepository();
        AddNewTopicPresenterInterface addNewTopicPresenter = new AddNewTopicPresenter(addNewTopicInterface, courseContentRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/AddNewTopicView.fxml", "Add new Topic", addNewTopicInterface, true);
    }
    public void openAddSubTopicWindow(ActionEvent event, Topic topic){
        AddNewSubTopicInterface addNewSubTopicInterface = new AddNewSubTopicController(topic);
        CourseContentRepositoryInterface courseContentRepositoryInterface = new CourseContentRepository();
        AddNewSubTopicPresenterInterface addNewSubTopicPresenterInterface = new AddNewSubTopicPresenter(addNewSubTopicInterface, courseContentRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/AddNewSubTopicView.fxml", "Add new Subtopic", addNewSubTopicInterface, true);
    }


    @Override
    public void setPresenter(ManageCourseContentPresenter presenter) {
        this.presenter = presenter;
    }

    private void addTopics(){
        rootItem.setExpanded(true);
        for (Topic topic : topics){
            presenter.getSubTopicByCourseID(topic.getID(), subTopics);
            TreeItem<CourseContentItem> topicItem = new TreeItem<>(topic);




            for(SubTopic subTopic: subTopics){
                if(topic.getID() == subTopic.getTopicID()){
                    topicItem.getChildren().add(new TreeItem<>(subTopic));
                }

            }
            topicItem.getChildren().add(new TreeItem<>(new SubTopic("[Add new subtopic]")));
            rootItem.getChildren().add(0,topicItem);






        }
        TreeItem<CourseContentItem> addTopicItem = new TreeItem<>(new Topic("[Add new topic]"));
        rootItem.getChildren().add(addTopicItem);
    }
}
