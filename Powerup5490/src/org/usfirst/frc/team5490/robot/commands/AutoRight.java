package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.PathRecord;
import org.usfirst.frc.team5490.robot.RobotMap;


/**
 *
 */
public class AutoRight extends AutonomousBase {

	
	// ToDo determine this....
	public static final double ScaleHeight = 800;
	public static final double SwitchHeight = 300;
	
	public static final double Off = 0;
	public static final double Fwd = 1;
	public static final double Rev = -1;
	public static final double Right = 1;
	public static final double Left = -1;
	public static final double CW = 1;
	public static final double CCW = -1;

	
	PathRecord[] 	ToLeftNearSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'
			new PathRecord(  Off,  Off, -90,      0, 0),		// rotate  90 left
			//
			new PathRecord(  Off,  Fwd, Off,    .65, 2),  		// forward 18'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off, 0,       0, 0),			// rotate back to 0
			
			new PathRecord(  Off,  Fwd, Off,    .49, 1.65),  	// forward 10'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off,  90,     0, 0),			// rotate right 90			
			new PathRecord(  Off,  Fwd, Off,    .4, 1),			// forward 2
   	};
	
	// tested
	PathRecord[] 	ToRightNearSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .45, 2),	 	// forward 5.7'			
			new PathRecord(  Off,  Off, 90,      0, 0),			// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),	 		// forward 2'
			new PathRecord(  Off,  Off, 0,       0, 0),			// rotate back to 0
			new PathRecord(  Off,  Fwd, Off,    .49, 1.65),  	// forward 10'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off, -90,     0, 0),			// rotate left 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),			// forward 2			
   	};
	


	// need testing
	PathRecord[] 	ToLeftNearSwitchBehind =  {
			new PathRecord(  Off,  Fwd, Off,    .65, 2.35),  		// forward 18'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// forward 2'
			
			new PathRecord(  Off,  Off, -90,      0, 0),			// rotate   90
			//
			new PathRecord(  Off,  Fwd, Off,    .65, 2),  		// forward 18'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// forward 10'
			
			new PathRecord(  Off,  Off, -180,      0, 0),			// rotate   90
			
			//bump into switch wall
			new PathRecord(  Off,  Fwd, Off,    .4, 2),			// forward 2
   	};
	
	
	/*
	PathRecord[] 	ToLeftFarSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'
			new PathRecord(  Off,  Off, -90,      0, 0),		// rotate  90 left
			//
			new PathRecord(  Off,  Fwd, Off,    .65, 2),  		// forward 18'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off, 0,       0, 0),			// rotate back to 0
			
			new PathRecord(  Off,  Fwd, Off,    .49, 1.65),  	// forward 10'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off,  90,     0, 0),			// rotate right 90			
			new PathRecord(  Off,  Fwd, Off,    .4, 1),			// forward 2
   	};
	
	// tested
	PathRecord[] 	ToRightFarSwitch =  {    			
			new PathRecord(  Off,  Fwd, Off,    .45, 2),	 	// forward 5.7'			
			new PathRecord(  Off,  Off, 90,      0, 0),			// rotate right 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),	 		// forward 2'
			new PathRecord(  Off,  Off, 0,       0, 0),			// rotate back to 0
			new PathRecord(  Off,  Fwd, Off,    .6, 5),  		// forward 30'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off, -90,     0, 0),			// rotate left 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),			// forward 2			
   	};
	

	

	// tested
	PathRecord[] 	ToLeftScale =  {  			

			new PathRecord(  Off,  Fwd, Off,    .4, 2),	 		// forward 5.7'
			new PathRecord(  Off,  Off, -90,      0, 0),		// rotate   90
			//
			new PathRecord(  Off,  Fwd, Off,    .65, 2),  		// forward 18'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off, 0,       0, 0),			// rotate left  90
			
			new PathRecord(  Off,  Fwd, Off,    .5, 5),  		// forward 25'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off,  90,     0, 0),			// rotate right 90			
			new PathRecord(  Off,  Fwd, Off,    .4, 1),			// forward 2			
   	};	
	//
	PathRecord[] 	ToRightScale =  {    			
			new PathRecord(  Off,  Fwd, Off,    .45, 2),	 	// forward 5.7'			
			new PathRecord(  Off,  Off, 90,      0, 0),			// rotate right  90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),	 		// forward 2'
			new PathRecord(  Off,  Off, 0,       0, 0),			// rotate right back to 0
			new PathRecord(  Off,  Fwd, Off,    .5, 5),  		// forward 25'
			new PathRecord(  Off,  Fwd, Off,     0, 0.25),  	// stop
			new PathRecord(  Off,  Off, -90,     0, 0),			// rotate left 90
			new PathRecord(  Off,  Fwd, Off,    .4, 1),			// forward 2			

   	};
   	*/

	

	
	PathSequence PathDriver;
	LiftSetpoint liftdriver;
	
    public AutoRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	state = AutoStart.Right;
    	
   	 	
    	
    	addParallel(new WinchToOperate());	// winch the lift to operate
    	//addParallel(new LiftDown());		// move gripper down to init PID position
    	PathDriver= new PathSequence();
    	addSequential(PathDriver);
    	//
    	//liftdriver = new LiftSetpoint(SwitchHeight);
    	//addSequential(liftdriver);    	
    	addSequential(new Forward2());
    	addSequential(new GripperRelease());
    	addSequential(new Reverse2());
    	addSequential(new LiftDown());    	

    }

    // Called just before this Command runs the first time
    // establish the path we want for the driving code.
    //
    // Explanation:
    //
    //  Data regarding plate assignment is provided to each robot based on their alliance. In other words, the 
    // Blue alliance will receive data corresponding to the location of the Blue plates and the Red alliance 
    // will receive data corresponding to the location of the Red plates. The data is referenced from the perspective 
    // of the Drive Team looking out from their Player Station. The data consists of three characters, each 'L' or 'R', 
    // representing the location (Left or Right) of the Alliance's plate on each element, starting with the element closest 
    // to the Alliance.    

    protected void initialize() {
    	 PathRecord[] p = ToRightNearSwitch; 
    	 
    	 if(gameData.length() > 0)        	
         {
         	switch(gameData) {
         	case "LLL":
         		p = ToLeftNearSwitchBehind;
         		break;
         	case "LLR":
         		p = ToLeftNearSwitchBehind;
         		break;
         	case "LRL":
         		p = ToLeftNearSwitchBehind;
         		// was to right scale
         		// liftdriver.ChangeSetpoint(RobotMap.ScaleHeight);
         		break;
         	case "LRR":
         		p = ToLeftNearSwitchBehind;
         		// was to right scale
         		//liftdriver.ChangeSetpoint(RobotMap.ScaleHeight);
         		break;
         	case "RLL":
         		p = ToRightNearSwitch;
         		break;
         	case "RLR":
         		p = ToRightNearSwitch;
         		break;
         	case "RRL":
         		p = ToRightNearSwitch;
         		//was to right scale
         		// liftdriver.ChangeSetpoint(RobotMap.ScaleHeight);
         		break;
         	case "RRR":
         		p = ToRightNearSwitch;
         		break;
         	default:
         		// can't decode message - just assume far right switch
         		p = ToRightNearSwitch;
         		break;
         	}
         } else {
        	 p = ToRightNearSwitch;
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
