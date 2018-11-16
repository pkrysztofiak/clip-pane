package com.github.pkrysztofiak.javafx.clippane;


import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class ClipPane extends Region {

    private final Rectangle clipRectangle = new Rectangle();

    private final ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("image.jpg")));
    private final ImageViewController imageViewController = new ImageViewController(imageView);
    
    public final Observable<Double> widthObservalbe = JavaFxObservable.valuesOf(widthProperty()).map(Number::doubleValue);
    public final Observable<Double> heightObservalbe = JavaFxObservable.valuesOf(heightProperty()).map(Number::doubleValue);
    
    {
        clipRectangle.widthProperty().bind(widthProperty());
        clipRectangle.heightProperty().bind(heightProperty());
        setClip(clipRectangle);

        getChildren().add(imageView);
        
        setStyle("-fx-background-color: black");
    }
    
    public ClipPane() {
        
        Observable.combineLatest(
                widthObservalbe, 
                imageViewController.widthObservable, 
                (width, imageWidth) -> (width - imageWidth) / 2.)
        .subscribe(imageView::setLayoutX);
        
        Observable.combineLatest(
                heightObservalbe, 
                imageViewController.heightObservable, 
                (height, imageHeight) -> (height - imageHeight) / 2.)
        .subscribe(imageView::setLayoutY);
    }
}