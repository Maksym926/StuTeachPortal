package com.EduTech.educationportal.app.view.shared;

import com.EduTech.educationportal.data.CourseContentRepository;
import com.EduTech.educationportal.data.UserRepository;
import com.EduTech.educationportal.interfaces.presenter.AddNewSubTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.presenter.AddNewTopicPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.repository.UserRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.*;
import com.EduTech.educationportal.model.aws.S3Uploader;
import com.EduTech.educationportal.model.entities.Course;
import com.EduTech.educationportal.model.entities.SubTopic;
import com.EduTech.educationportal.model.entities.Topic;
import com.EduTech.educationportal.presenter.shared.AddNewSubTopicPresenter;
import com.EduTech.educationportal.presenter.shared.AddNewTopicPresenter;
import com.EduTech.educationportal.presenter.shared.ManageCourseContentPresenter;
import com.EduTech.educationportal.utils.Log;
import com.EduTech.educationportal.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @FXML private Text courseTitle;
    @FXML private Text subTopicTitle;
    @FXML private Text mainContent;
    @FXML private Text fileName;
    Topic selectedTopic = null;
    SubTopic selectedSubTopic = null;


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
                            openAddTopicWindow(new ActionEvent(e.getSource(), null), null);
                        }
                    });
                } else if (title.equals("[Add new subtopic]")) {
                    setStyle("-fx-text-fill: green; -fx-underline: true;");
                    setOnMouseClicked(e -> {
                        if (e.getClickCount() == 1) {
                            TreeItem<CourseContentItem> parentItem = getTreeItem().getParent();
                            if(parentItem != null){
                                Topic topic = (Topic)parentItem.getValue();
                                openAddSubTopicWindow(new ActionEvent(e.getSource(),null), topic, null);
                            }

                        }
                    });
                }
            }
        });
        courseContentList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection.getValue() instanceof SubTopic subTopic) {
                selectedSubTopic = subTopic;
                subTopicTitle.setText(subTopic.getTitle());
                mainContent.setText(subTopic.getContent());
                fileName.setText(subTopic.getFileName());
            }
        });
        fileName.setOnMouseClicked(e ->{
            if(selectedSubTopic != null)
                presenter.downloadAssignment(selectedSubTopic.getFilePath(), selectedSubTopic.getFileName());
        });
    }
    @Override
    public void setup() {

        topics.clear();
        subTopics.clear();
        courseTitle.setText(course.getTitle());
        setupTree();
        presenter.getTopicByCourseID(course.getID(), topics);
        addTopics();
        if (selectedSubTopic != null){
            subTopicTitle.setText(selectedSubTopic.getTitle());
            mainContent.setText(selectedSubTopic.getContent());
            fileName.setText(selectedSubTopic.getFileName());

        }
    }

    public void openAddTopicWindow(ActionEvent event, Topic topic){
        AddNewTopicInterface addNewTopicInterface = new AddNewTopicController(course, topic);
        CourseContentRepositoryInterface courseContentRepositoryInterface = new CourseContentRepository();
        AddNewTopicPresenterInterface addNewTopicPresenter = new AddNewTopicPresenter(addNewTopicInterface, courseContentRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/AddNewTopicView.fxml", "Add new Topic", addNewTopicInterface, true);
    }
    public void openAddSubTopicWindow(ActionEvent event, Topic topic, SubTopic subTopic){
        AddNewSubTopicInterface addNewSubTopicInterface = new AddNewSubTopicController(topic,course, subTopic);
        CourseContentRepositoryInterface courseContentRepositoryInterface = new CourseContentRepository();
        S3Uploader s3Uploader = new S3Uploader();
        UserRepositoryInterface userRepositoryInterface = new UserRepository();
        AddNewSubTopicPresenterInterface addNewSubTopicPresenterInterface = new AddNewSubTopicPresenter(addNewSubTopicInterface, courseContentRepositoryInterface, s3Uploader, userRepositoryInterface);
        ViewNavigator.switchScene((Node)event.getSource(), "/AddNewSubTopicView.fxml", "Add new Subtopic", addNewSubTopicInterface, true);
    }

    @FXML
    public void deleteCourseContentItem(){
        TreeItem<CourseContentItem> selectedItem = courseContentList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Object value = selectedItem.getValue();

            if (value instanceof Topic) {
                Topic topic = (Topic) value;
                presenter.deleteTopic(topic.getID());
                // delete logic for topic
            }
            else if (value instanceof SubTopic) {
//                TreeItem<CourseContentItem> subTopicItem = getTreeItem();
////                SubTopic subTopic = (SubTopic) value;
////                TreeItem<CourseContentItem> topicItem = subTopic
////                Topic topic = subTopic.getParent();
//
//                presenter.deleteSubTopic(subTopic.getID());
                // delete logic for subtopic
            }
            setup();
        }
    }
    @FXML
    public void editCourseContent(ActionEvent event){
        TreeItem<CourseContentItem> selectedItem = courseContentList.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Object value =selectedItem.getValue();
            if(value instanceof Topic topic){
                openAddTopicWindow(event, topic);
            }
            else if(value instanceof SubTopic subTopic){
                openAddSubTopicWindow(event, t);
            }

        }


    }
    @FXML
    public void returnToPreviousForm(ActionEvent event){
        Log.info("Returning to previous form");
        ViewNavigator.goBack((Node) event.getSource());
    }

    @Override
    public void setPresenter(ManageCourseContentPresenter presenter) {
        this.presenter = presenter;
    }

    public Stage getStage(){
        return (Stage)fileName.getScene().getWindow();
    }

    private void addTopics(){
        rootItem.setExpanded(true);
        for (Topic topic : topics){
            presenter.getSubTopicByCourseID(topic.getID(), subTopics);
            TreeItem<CourseContentItem> topicItem = new TreeItem<>(topic);




            for(SubTopic subTopic: subTopics){
                if(topic.getID() == subTopic.getTopicID()){
                    topicItem.getChildren().add(new TreeItem<>(subTopic));
                    if (selectedSubTopic == null) {
                        selectedSubTopic = subTopic; // store the first match

                    }
                }

            }
            topicItem.getChildren().add(new TreeItem<>(new SubTopic("[Add new subtopic]")));
            rootItem.getChildren().add(0,topicItem);






        }
        TreeItem<CourseContentItem> addTopicItem = new TreeItem<>(new Topic("[Add new topic]"));
        rootItem.getChildren().add(addTopicItem);
    }
}
