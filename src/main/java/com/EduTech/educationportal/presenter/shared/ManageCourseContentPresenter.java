package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.interfaces.presenter.ManageCourseContentPresenterInterface;
import com.EduTech.educationportal.interfaces.repository.CourseContentRepositoryInterface;
import com.EduTech.educationportal.interfaces.view.ManageCourseContentInterface;
import com.EduTech.educationportal.model.aws.S3Downloader;
import com.EduTech.educationportal.model.entities.SubTopic;
import com.EduTech.educationportal.model.entities.Topic;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class ManageCourseContentPresenter implements  ManageCourseContentPresenterInterface{
    ManageCourseContentInterface view;
    CourseContentRepositoryInterface courseContentRepository;
    S3Downloader downloader;

    public ManageCourseContentPresenter(ManageCourseContentInterface view, CourseContentRepositoryInterface courseContentRepository, S3Downloader downloader){
        this.view = view;
        this.courseContentRepository = courseContentRepository;
        this.downloader = downloader;
        view.setPresenter(this);
    }
    public void getTopicByCourseID(int ID, List<Topic> topics){
        courseContentRepository.getTopicByCourseID(ID, topics);
    }
    public void getSubTopicByCourseID(int ID, List<SubTopic> subTopics){
        courseContentRepository.getSubTopicByTopicID(ID, subTopics);
    }

    public void deleteTopic(int ID){
        courseContentRepository.deleteTopic(ID);
    }
    public void deleteSubTopic(int ID){
        courseContentRepository.deleteSubTopic(ID);
    }

    public void downloadAssignment(String key, String fileName){
        Stage currentStage = view.getStage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Download Title");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File selectedDirectory = directoryChooser.showDialog(currentStage);
        Path destination = selectedDirectory.toPath().resolve(fileName);
        downloader.downloadAssignment(key, destination);

    }
}
