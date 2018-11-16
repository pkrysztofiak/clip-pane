package com.github.pkrysztofiak.javafx.clippane;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageViewController {

    private final ImageView imageView;
    
    public final Observable<Double> widthObservable;
    public final Observable<Double> heightObservable;
    
    public final Observable<Double> mousePressedLayoutXObservable;
    public final Observable<Double> mousePressedLayoutYObservable;
    
    public final Observable<Double> mousePressedXObservable;
    public final Observable<Double> mousePressedYObservable;
    
    public final Observable<Double> mouseDraggedXObservable;
    public final Observable<Double> mouseDraggedYObservable;
    
    
    public ImageViewController(ImageView imageView) {
        this.imageView = imageView;
        
        widthObservable = JavaFxObservable.valuesOf(imageView.getImage().widthProperty()).map(Number::doubleValue);
        heightObservable = JavaFxObservable.valuesOf(imageView.getImage().heightProperty()).map(Number::doubleValue);
        
        
        mousePressedXObservable = JavaFxObservable.eventsOf(imageView, MouseEvent.MOUSE_PRESSED).map(MouseEvent::getScreenX).map(Number::doubleValue);
        mousePressedYObservable = JavaFxObservable.eventsOf(imageView, MouseEvent.MOUSE_PRESSED).map(MouseEvent::getScreenY).map(Number::doubleValue);
        
        mousePressedLayoutXObservable = mousePressedXObservable.map(mouseEvent -> imageView.getLayoutX());
        mousePressedLayoutYObservable = mousePressedYObservable.map(mouseEvent -> imageView.getLayoutY());
        
        mouseDraggedXObservable = JavaFxObservable.eventsOf(imageView, MouseEvent.MOUSE_DRAGGED).map(MouseEvent::getScreenX).map(Number::doubleValue);
        mouseDraggedYObservable = JavaFxObservable.eventsOf(imageView, MouseEvent.MOUSE_DRAGGED).map(MouseEvent::getScreenY).map(Number::doubleValue);
        
        mouseDraggedXObservable
        .withLatestFrom(mousePressedXObservable, (draggedX, pressedX) -> draggedX - pressedX)
        .withLatestFrom(mousePressedLayoutXObservable, (deltaDragX, layoutX) -> deltaDragX + layoutX)
        .subscribe(imageView::setLayoutX);
        
        mouseDraggedYObservable
        .withLatestFrom(mousePressedYObservable, (draggedY, pressedY) -> draggedY - pressedY)
        .withLatestFrom(mousePressedLayoutYObservable, (deltaDragY, layoutY) -> deltaDragY + layoutY)
        .subscribe(imageView::setLayoutY);
    }
    
//    private double calculateDraggedDeltaX(double draggedX, double pressedX) {
//        return draggedX - pressedX;
//    }
//    
//    private double calculateDraggedDeltaY(double draggedY, double pressedY) {
//        return draggedY - pressedY;
//    }
//    
//    private double calculateDraggedLayoutX(double dragged, double layout) {
//        return layout + dragged;
//    }
//    
//    private double calculateDraggedLayoutY(double dragged, double layout) {
//        return layout + dragged;
//    }
//    
//    public void onLayoutXChanged(double layoutX) {
//        imageView.setLayoutX(layoutX);
//    }
//    
//    public void onLayoutYChanged(double layoutX) {
//        imageView.setLayoutY(layoutX);
//    }
}
