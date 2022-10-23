import java.awt.*;

import javax.swing.text.Position;


public class Worldclass{
    private int width;
	private int height;
	private Agentclass[] agents;
	private final int numAgents =10 ;
	private final int margin = 30;



    //constructor
    public Worldclass(int w, int h, int numA){
        width = w;
        height = h;
        agents = new Agentclass[numAgents];

        for(int i = 0; i <numAgents; i++){
            agents[i] = new Agentclass(randomPos(), randomRadius());
            agents[i].SetSpeed(1);
            agents[i].SetTarget(randomPos());
        }
    }

    private Vec2D randomPos(){
		double x = margin + Math.random() * (width - 2*margin);
		double y = margin + Math.random() * (height - 2*margin);
		return new Vec2D(x, y);
	} 

	private double randomRadius(){
		return 5 + Math.random()* ( margin -5);
	}
    

    public void simulationStep(){
        for(int i = 0; i <numAgents; i++){
            if(agents[i].TargetReached()== true){
                agents[i].SetTarget(randomPos());
            }
            else{
                agents[i].UpdatePosition();
            }
        
            if (40 > agents[i].getPosition().getX()  || agents[i].getPosition().getX() > 760 || 40 > agents[i].getPosition().getY()  || agents[i].getPosition().getY() > 560) {
                agents[i].SetTarget(randomPos());
                }
                

            

        }     
    }
        

    public void paint(Graphics g){
        for ( int i = 0; i<numAgents; i++){
            agents[i].paint(g);
        }
    }
    public void set_newTarget(Agentclass agent){
            
            double x =  agent.getPosition().getX() - agent.getTarget().getX();
            double y =  agent.getPosition().getY() - agent.getTarget().getY();
        
            Vec2D newTarget = new Vec2D(x,y);
            newTarget.add(agent.getPosition());
            agent.SetTarget(newTarget);

    }
    
    public void manageCollisions(){
        for(int i=0; i<numAgents; i++){
            for(int j=i+1; j<numAgents; j++){
                if(agents[i].isColliding(agents[j]) == true ){
                    set_newTarget(agents[i]);
                    set_newTarget(agents[j]);

                }
            }
        }
    }


}