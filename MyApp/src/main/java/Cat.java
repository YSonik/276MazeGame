
public class Cat {
    private int currentX;
    private int currentY;
    private final int catHeight = 25;
    private final int catWidth = 25;

    Cat(int startX, int startY)
    {
<<<<<<< HEAD
        this.currentX = currentX;
        this.currentY = currentY;
=======
        this.currentX = startX;
        this.currentY = startY;
>>>>>>> master
    }

    public void chase (int mouseCurrentX, int mouseCurrentY, Tile [][] map) {
        int distanceX = mouseCurrentX - this.getCurrentX();
        int distanceY = mouseCurrentY - this.getCurrentY();
<<<<<<< HEAD


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

=======
        if (distanceX > 0 || (distanceX > 0 && distanceY == 0)) {
            if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    return 1;
            }
            else{

                    if(map[this.getCurrentY()][this.getCurrentY() - 1].getisBarrier() == false){
                        this.setCurrentY(this.getCurrentY() - 1);
                            System.out.println("return 4");
                            return 4;
                    }
                    else if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() - 1);
                        return 2;
                    }
                    else {
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
                        return 3;
                    }

            }


        } else if (distanceX < 0 || (distanceX < 0 && distanceY == 0)){
            if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }
            else{

                if(map[this.getCurrentY()][this.getCurrentY() - 1].getisBarrier() == false){
                    this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 4");
                        return 4;
                }
                else if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    return 1;
                }
                else {
                    this.setCurrentY(this.getCurrentY() + 1);
                    System.out.println("return 3");
                    return 3;
                }

            }



        } else {
            if (distanceY > 0 && distanceX == 0 ) {
                if (map[this.getCurrentY() + 1][this.getCurrentX()].getisBarrier() == false) {
                    this.setCurrentY(this.getCurrentY() + 1);
                    System.out.println("return 3");
                    return 3;
                }

                else{

                    if(map[this.getCurrentY()][this.getCurrentY() - 1].getisBarrier() == false){
                        this.setCurrentY(this.getCurrentY() - 1);
                            System.out.println("return 4");
                            return 4;
                    }
                    else if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() + 1);
                        return 1;
                    }
                    else {
                        this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 2");
                        return 2;
                    }
    
                }


            } else if (distanceY < 0 && distanceX == 0){
                if (map[this.getCurrentY() - 1][this.getCurrentX()].getisBarrier() == false) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    System.out.println("return 4");
                    return 4;
                }
                else{

                    if(map[this.getCurrentY()][this.getCurrentY() + 1].getisBarrier() == false){
                        this.setCurrentY(this.getCurrentY() + 1);
                            System.out.println("return 3");
                            return 3;
                    }
                    else if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() + 1);
                        return 1;
                    }
                    else {
                        this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 2");
                        return 2;
                    }
    
                }
            } else {
                return 0;
            }
        }
>>>>>>> master
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
