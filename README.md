# ClipPane

Showcase for JavaFx clip pane.
Demo shows 3x3 GridPane with the image in every cell. Every cell clips its content.
Images are draggable to demonstrate clipping feature.

## Core
To clip a pane we need a rectangle which works as clipping area supplier for the pane. Rectangle width/height is bound to pane width/height to keep the clipping area accurate if size of the pane changes. 
```java
public class ClipPane extends Region {

    private final Rectangle clipRectangle = new Rectangle();

    private final ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("image.jpg")));
    
    {
        clipRectangle.widthProperty().bind(widthProperty());
        clipRectangle.heightProperty().bind(heightProperty());
        setClip(clipRectangle);

        getChildren().add(imageView);
    }
}
```
