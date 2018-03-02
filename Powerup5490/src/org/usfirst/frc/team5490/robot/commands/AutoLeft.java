package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.PathRecord;

/**
 * Auto command for the Left Starting position
 */
public class AutoLeft extends AutonomousBase {
	
	public static final double Off = 0;
	public static final double Fwd = 1;
	public static final double Rev = -1;
	public static final double Right = 1;
	public static final double Left = -1;
	public static final double CW = 1;
	public static final double CCW = -1;
	
	public static final double deg90 = 0.4;		// speed to turn 90deg in time90
	public static final double time90 = 1;		// time to run 90deg at deg90
	
	PathRecord[] 	ToLeftNearSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord( Left,  Off, Off,    .4, 1),  			// Left 2'
			new PathRecord(  Off,  Fwd, Off,    .5, 2),  			// forward 10'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
   	};
	PathRecord[] 	ToRightNearSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord(Right,  Off, Off,    .65, 2),  			// Right 18'
			new PathRecord(  Off,  Fwd, Off,    .5, 2),  			// forward 10'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
			
   	};
	PathRecord[] 	ToLeftScale =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord( Left,  Off, Off,    .4, 1),  			// Left 2'
			new PathRecord(  Off,  Fwd, Off,    .5, 5),  			// forward 25'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
   	};
	PathRecord[] 	ToRightScale =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord(Right,  Off, Off,    .65, 2),  			// Right 18'
			new PathRecord(  Off,  Fwd, Off,    .5, 5),  			// forward 25'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2		

   	};
	PathRecord[] 	ToLeftFarSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord( Left,  Off, Off,    .4, 1),  			// Left 2'
			new PathRecord(  Off,  Fwd, Off,    .6, 4),  			// forward 30'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
   	};
	PathRecord[] 	ToRightFarSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord(Right,  Off, Off,    .65, 2),  			// Right 18'
			new PathRecord(  Off,  Fwd, Off,    .6, 4),  			// forward 30'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2
   	};
	
	PathSequence PathDriver;
	

    public AutoLeft() {    	
    	
    	state = AutoStart.Left;
    	
    	addParallel(new WinchToOperate());
    	PathDriver= new PathSequence();
    	addSequential(PathDriver);   
    }

    // Called just before this Command runs the first time
    // establish the path we want for the driving code.
    protected void initialize() {
        
   	 	PathRecord[] p = ToRightFarSwitch; 
   	 
		 if(gameData.length() > 0)        	
	     {
	     	switch(gameData) {
	     	case "LLL":
	     		p = ToLeftFarSwitch;
	     		break;
	     	case "LLR":
	     		p = ToLeftFarSwitch;
	     		break;
	     	case "LRL":
	     		p = ToLeftFarSwitch;
	     		break;
	     	case "LRR":
	     		p = ToLeftNearSwitch;
	     		break;
	     	case "RLL":
	     		p = ToLeftScale;
	     		break;
	     	case "RLR":
	     		p = ToLeftScale;
	     		break;
	     	case "RRL":
	     		p = ToLeftFarSwitch;
	     		break;
	     	case "RRR":
	     		p = ToRightScale;
	     		break;
	     	default:
	     		// can't decode message - just assume far right switch
	     		p = ToRightFarSwitch;
	     		break;
	     	}
	     }
		 PathDriver.path = p;

    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
