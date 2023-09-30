package org.firstinspires.ftc.teamcode;

public class Launch {
    Hardware robot = null;

    public Launch(Hardware laRobot) { robot = laRobot;}

    public void release(){
        robot.trigger.setPosition(robot.triggerFire);

        while(robot.trigger.getPosition() != robot.triggerFire){

        }
        robot.trigger.setPosition(robot.triggerLock);
    }

}
