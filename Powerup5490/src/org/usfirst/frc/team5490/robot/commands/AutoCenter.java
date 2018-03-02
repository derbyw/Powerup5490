package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.PathRecord;

/**
 *
 */
public class AutoCenter extends AutonomousBase {

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
			new PathRecord( Left,  Off, Off,    .57, 2),  			// Left 13'
			new PathRecord(  Off,  Fwd, Off,    .5, 2),  			// forward 10'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
   	};
	PathRecord[] 	ToRightNearSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord(Right,  Off, Off,    .5, 2),  			// Right 10'
			new PathRecord(  Off,  Fwd, Off,    .5, 2),  			// forward 10'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
			
   	};
	PathRecord[] 	ToLeftScale =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord( Left,  Off, Off,    .57, 2),  			// Left 13'
			new PathRecord(  Off,  Fwd, Off,    .5, 5),  			// forward 25'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
   	};
	PathRecord[] 	ToRightScale =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord(Right,  Off, Off,    .5, 2),  			// Right 10'
			new PathRecord(  Off,  Fwd, Off,    .5, 5),  			// forward 25'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2		

   	};
	PathRecord[] 	ToLeftFarSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord( Left,  Off, Off,    .57, 2),  			// Left 13'
			new PathRecord(  Off,  Fwd, Off,    .6, 4),  			// forward 30'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2			
   	};
	PathRecord[] 	ToRightFarSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'			
			new PathRecord(Right,  Off, Off,    .5, 2),  			// Right 10'
			new PathRecord(  Off,  Fwd, Off,    .6, 4),  			// forward 30'
			new PathRecord(  Off,  Off,  CW, deg90, time90),	// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),				// forward 2
   	};
	
	PathSequence PathDriver;
	
    public AutoCenter() {
    	state = AutoStart.Center;
    	
    	addParallel(new WinchToOperate());    	
    	PathDriver= new PathSequence();
    	addSequential(PathDriver);
    }

    // Called just before this Command runs the first time
    // establish the path we want for the driving code.
    protected void initialize() {    	

   	 	PathRecord[] p = ToRightScale; 
	 
		 if(gameData.length() > 0)        	
	     {
	     	switch(gameData) {
	     	case "LLL":
	     		p = ToLeftNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "LLR":
	     		p = ToLeftNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "LRL":
	     		p = ToLeftNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "LRR":
	     		p = ToLeftNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "RLL":
	     		p = ToRightNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "RLR":
	     		p = ToRightNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "RRL":
	     		p = ToRightNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	case "RRR":
	     		p = ToRightNearSwitch;
	     		addSequential(new DeliverSwitch());
	     		break;
	     	default:
	     		// can't decode message - just assume far right switch
	     		p = ToRightScale;
	     		addSequential(new DeliverScale());
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
