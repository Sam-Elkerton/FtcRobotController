package org.firstinspires.ftc.teamcode;

public class Launch {
    Hardware robot = null;

    public Launch(Hardware laRobot) { robot = laRobot;}

    public void release(){
        robot.RightClaw.setPosition(robot.RightClawOpen);
        robot.LeftClaw.setPosition(robot.LeftClawOpen);

        while(robot.RightClaw.getPosition() != robot.RightClawOpen){

        }
        robot.RightClaw.setPosition(robot.RightClawClosed);
        robot.LeftClaw.setPosition(robot.LeftClawClosed);
    }

}
