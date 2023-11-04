package org.firstinspires.ftc.teamcode;

import android.telephony.mbms.MbmsErrors;

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

    public Drive(Hardware pRobot) {
        robot = pRobot;
    }

    public void PowerDrive(double pFL, double pFR, double pBR, double pBL) {
        robot.LeftDriveRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.LeftDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.RightDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.RightDriveRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.LeftDriveRear.setPower(pBL);
        robot.RightDriveRear.setPower(pBR);
        robot.LeftDriveFront.setPower(pFL);
        robot.RightDriveFront.setPower(pFR);

        robot.LeftDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LeftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void EncoderDrive(double speed, double leftInches, double rightInches, double timeout, double degrees) {
        double pSpeed = speed;

        InitializeEncoders(leftInches, rightInches, degrees);

        if (degrees > 0) {
            pSpeed = .2;
        }

        robot.LeftDriveRear.setPower(Math.abs(pSpeed));
        robot.RightDriveRear.setPower(Math.abs(pSpeed));
        robot.LeftDriveFront.setPower(Math.abs(pSpeed));
        robot.RightDriveFront.setPower(Math.abs(pSpeed));


        while (robot.LeftDriveRear.isBusy() & robot.RightDriveRear.isBusy()) {


        }
        robot.LeftDriveRear.setPower(0);
        robot.RightDriveRear.setPower(0);
        robot.LeftDriveFront.setPower(0);
        robot.RightDriveFront.setPower(0);
    }

    public void InitializeEncoders(double leftInches, double rightInches, double degrees) {

        robot.LeftDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LeftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        CalculateTarget(leftInches, rightInches, degrees);

        robot.LeftDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RightDriveRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.LeftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void CalculateTarget(double leftInches, double rightInches, double degrees) {

        int newLeftTarget;
        int newRightTarget;
        double targetInches;

        if (degrees == 0) {

            newLeftTarget = robot.LeftDriveRear.getCurrentPosition() + (int) (leftInches * robot.COUNTS_PER_INCH);
            newRightTarget = robot.RightDriveRear.getCurrentPosition() + (int) (rightInches * robot.COUNTS_PER_INCH);
            robot.LeftDriveRear.setTargetPosition(newLeftTarget);
            robot.RightDriveRear.setTargetPosition(newRightTarget);

            newLeftTarget = robot.LeftDriveFront.getCurrentPosition() + (int) (leftInches * robot.COUNTS_PER_INCH);
            newRightTarget = robot.RightDriveFront.getCurrentPosition() + (int) (rightInches * robot.COUNTS_PER_INCH);
            robot.LeftDriveFront.setTargetPosition(newLeftTarget);
            robot.RightDriveFront.setTargetPosition(newRightTarget);
        } else {
            targetInches = (robot.WHEEL_DIAMETER_INCHES * 3.1415 * (Math.abs(degrees) / (int) 45)) - 2;
            if (degrees > 0) {

                newLeftTarget = robot.LeftDriveRear.getCurrentPosition() + (int) (targetInches * robot.COUNTS_PER_INCH);
                newRightTarget = robot.RightDriveRear.getCurrentPosition() - (int) (targetInches * robot.COUNTS_PER_INCH);
                newLeftTarget = robot.LeftDriveFront.getCurrentPosition() + (int) (targetInches * robot.COUNTS_PER_INCH);
                newRightTarget = robot.RightDriveFront.getCurrentPosition() - (int) (targetInches * robot.COUNTS_PER_INCH);
            } else {
                newLeftTarget = robot.LeftDriveRear.getCurrentPosition() - (int) (targetInches * robot.COUNTS_PER_INCH);
                newRightTarget = robot.RightDriveRear.getCurrentPosition() + (int) (targetInches * robot.COUNTS_PER_INCH);
                newLeftTarget = robot.LeftDriveFront.getCurrentPosition() - (int) (targetInches * robot.COUNTS_PER_INCH);
                newRightTarget = robot.RightDriveFront.getCurrentPosition() + (int) (targetInches * robot.COUNTS_PER_INCH);
            }

            robot.LeftDriveFront.setTargetPosition(newLeftTarget);
            robot.RightDriveFront.setTargetPosition(newRightTarget);
            robot.LeftDriveRear.setTargetPosition(newLeftTarget);
            robot.RightDriveRear.setTargetPosition(newRightTarget);


        }
    }
}

