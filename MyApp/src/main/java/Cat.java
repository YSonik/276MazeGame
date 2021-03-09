public class Cat {
    private int currentX;
    private int currentY;
    private final int catHeight = 25;
    private final int catWidth = 25;

    Cat()
    {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public void chase (int mouseCurrentX, int mouseCurrentY, Tile [][] map) {
        int distanceX = mouseCurrentX - this.getCurrentX();
        int distanceY = mouseCurrentY - this.getCurrentY();


        //if mouse is on the left
        if (distanceX < 0) {
            
            if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
            }

            //left is blocked, go to bottom
            else if(map[this.getCurrentY() + 1][this.getCurrentX()].getisBarrier() == false ){
                            this.setCurrentY(this.getCurrentY() + 1);
                            
            }
            //if left and bottom are blocks, go up
            else if (map[this.getCurrentY() - 1][this.getCurrentX()].getisBarrier() == false ){
                this.setCurrentY(this.getCurrentY() - 1);
                            
            }                       
            //otherwise go right
            else {
                this.setCurrentX(this.currentX + 1);
                            
            }            
                     
            
        }
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //mouse is on the right
        else if (distanceX > 0) {
            
            if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() + 1);
              
            }
            //right is blocked
            else if(map[this.getCurrentY() + 1][this.getCurrentX()].getisBarrier() == false ){
                            this.setCurrentY(this.getCurrentY() + 1);
                            
            }
            //if right and bottom are blocks, go up
            else if (map[this.getCurrentY() - 1][this.getCurrentX()].getisBarrier() == false ){
                            this.setCurrentY(this.getCurrentY() - 1);
                           
            }            
            //otherwise go left
            else {
                 this.setCurrentX(this.currentX - 1);           
                          
            }                                   
                    
            
        }
        else if (distanceX == 0){
            // mouse is at the bottom
            if(distanceY > 0){
                if (map[this.getCurrentY()+1][this.getCurrentX()].getisBarrier() == false) {
                    this.setCurrentY(this.getCurrentY() + 1);
                  
                }
                else if (map[this.getCurrentY()][this.getCurrentX()+1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() + 1);
                  
                }
                else if (map[this.getCurrentY()][this.getCurrentX()-1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() - 1);
                  
                }
                else {
                    this.setCurrentY(this.getCurrentY() - 1);
                }
            }
            //mouse at the top
            else{
                if (map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier() == false) {
                    this.setCurrentY(this.getCurrentY() - 1);
                  
                }
                else if (map[this.getCurrentY()][this.getCurrentX()+1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() + 1);
                  
                }
                else if (map[this.getCurrentY()][this.getCurrentX()-1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() - 1);
                  
                }
                else {
                    this.setCurrentY(this.getCurrentY() + 1);
                }
                
            }
        }

    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY; }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
}
