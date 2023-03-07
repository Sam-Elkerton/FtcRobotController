package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.Position;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


public class Drive {
    private ElapsedTime runTime = new ElapsedTime();

    private Hardware robot = null;

    public Drive(Hardware pRobot){
        robot = pRobot;
    }

    //this drive is FieldCentric Drive which means that all movements of the robot based on controller inputs
    //are relative to the field and not the robot
    //think of it like a compass, instead of pressing forward on joystick make the robot go forward relative to the
    //front of the robot , it instead will go north on the field, no matter the orientation of the robot

    public void EncoderDrive(int frDistance, int flDistance, int blDistance, int brDistance ){

    }



}
