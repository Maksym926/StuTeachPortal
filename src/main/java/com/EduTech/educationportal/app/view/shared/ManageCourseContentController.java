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
import javafx.scene.control.TreeCell;
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
    TreeItem<String> rootItem = new TreeItem<>("Topics");

    public ManageCourseContentController(Course course){
        this.course = course;
        Log.info("The course was parsed "  + course.getID());
    }

    @Override
    public void setup() {
        presenter.getTopicByCourseID(course.getID(), topics);
        addTopics();
        TreeItem<String> addTopicItem = new TreeItem<>("[Add new topic]");
        rootItem.getChildren().add(addTopicItem);



        courseContentList.setCellFactory(tv -> new TreeCell<String>(){
            @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item, empty);
                if(empty || item == null){
                    setText(null);
                    setStyle(null);
                    setOnMouseClicked(null);
                }
                else{
                    setText(item);
                    if(item.equals("[Add new topic]")){
                        setStyle("-fx-text-fill: blue; -fx-underline: true;");
                        setOnMouseClicked(e -> {
                            if(e.getClickCount() == 1){
//                                openAddTopicWindow(ActionEvent event);
                            }
                        });
                    }
                    else if(item.equals("[Add new subtopic]")){
                        setStyle("-fx-text-fill: green; -fx-underline: true;");
                        setOnMouseClicked(e ->{
                            if(e.getClickCount() == 1){
                                TreeItem<String> parent = getTreeItem().getParent();
//                                openAddSubtopicDialog()
                            }
                        });
                    }
                }
            }
        });






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

    private void addTopics(){
        rootItem.setExpanded(true);
        for (Topic topic : topics){
            int insertPos = rootItem.getChildren().size() -1;
            if(insertPos>= 0)
                rootItem.getChildren().add(insertPos, new TreeItem<>(topic.getTitle()));
            else
                rootItem.getChildren().add(new TreeItem<>(topic.getTitle()));
        }
    }
}
