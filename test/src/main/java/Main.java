import java.util.ArrayDeque;
import java.util.Iterator;

public class Main {

    static class Snake {

        private int length = 10;
        private int direction = 0;
        private ArrayDeque<Part> parts;

        public Snake(){
            this.parts = new ArrayDeque<Part>();
            this.parts.addFirst(new Part(Constants.WIDTH/2,Constants.HEIGHT/2));
        }

        private boolean eatMySelf(){

            Iterator iter = getPartsIterator();

            Part snakeHead = (Part)iter.next();

            Part temp_part;

            while( iter.hasNext() ){

                temp_part = (Part)iter.next();

                if( Math.abs(temp_part.x - snakeHead.x) < Constants.block_size &&
                        Math.abs(temp_part.y - snakeHead.y ) < Constants.block_size
                ){
                    return true;
                }
            }
            return false;
        }

        public void setDirection(int dir){
            this.direction = dir;
        }

        public Iterator getPartsIterator(){
            return this.parts.iterator();
        }

        public Part Tail(){

            return (Part)this.parts.getLast();
        }

        public boolean move(){

            int dx = ((direction == Constants.DIR_RIGHT)?Constants.block_size:((direction == Constants.DIR_LEFT)?-Constants.block_size:0));
            int dy = ((direction == Constants.DIR_DOWN)?Constants.block_size:((direction == Constants.DIR_UP)?-Constants.block_size:0));

            Part tail = this.Tail();

            int new_x = tail.x + dx;
            int new_y = tail.y + dy;

            new_x %= Constants.WIDTH;
            new_y %= Constants.HEIGHT;

            new_x += ((new_x < 0)?Constants.WIDTH:0);
            new_y += ((new_y < 0)?Constants.HEIGHT:0);

            Part temp = new Part(new_x,new_y);
            this.parts.addFirst(temp);

            if( this.length <= this.parts.size() ){
                parts.removeFirst();
            }

            return this.eatMySelf();
        }

    }

    static class Part {

        public int x;
        public int y;

        Part(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Constants{

        static int block_size = 5;
        static int WIDTH = 800;
        static int HEIGHT = 600;
        static int DIR_RIGHT = 0;
        static int DIR_UP = 1;
        static int DIR_LEFT = 2;
        static int DIR_DOWN = 3;
    }


    public static void main(String[] args) {}

}
