public class Brick extends Sprite 
{

    public Brick(int x, int y) 
    {
        super(x, y);

        initBrick();
    }

    private void initBrick()
    {
        loadImage("Brick.png");
        getImageDimensions();//Dimenssions 12 x 25
        Game.brickmade();
    }
}