import java.awt.*;
import java.nio.channels.NetworkChannel;

public class Agentclass{
    private Vec2D position;
    private Vec2D direction;
    private Vec2D target;
    private double radius;
    private double speed;
 
 

    //constructor
    public Agentclass(Vec2D p, double r){
        position  = p;
        radius = r;
    } 

    //methods
    public Vec2D getPosition(){
        return position;
    }

    public Vec2D getTarget(){
        return target;
    }
    public void SetTarget(Vec2D t){
        target = t; 
        direction = new Vec2D(t) ;
        direction.subtract(position);
        direction.normalize();
    } 
    


    public void SetSpeed( double s){
        speed = s;

    }

    public void  UpdatePosition(){
        double NewPosX = speed* direction.getX();
        double NewPosY = speed * direction.getY();

        Vec2D new_position= new Vec2D(NewPosX, NewPosY);
        position.add(new_position);
    }

    public boolean TargetReached(){
        Vec2D x = new Vec2D(target);
        x.subtract(position);
        
        if (x.length() < radius){
            return true;
        }
        return false;
    }

    
    
    public boolean isColliding(Agentclass agent){
        Vec2D x = new Vec2D(agent.position);
        x.subtract(position);
        double total_radius = agent.radius + radius;
        if (x.length() < total_radius){
            return true;
        }
        return false;
    
    
    }

    public void paint(Graphics g){
        int x = (int) (position.getX() - radius);
		int y = (int) (position.getY() - radius);
		int d = (int) (2*radius);
        g.setColor(Color.GREEN);
        g.fillOval(x, y, d, d);
        
    
    }
    
}