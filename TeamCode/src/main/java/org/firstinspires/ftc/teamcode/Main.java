package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "K", group = "TeleOp")
public class Main extends LinearOpMode{
    //variable declarations
    private ElapsedTime runtime = new ElapsedTime();
    //hardware call
    private Hardware robot   = new Hardware();


    @Override
    public void runOpMode() {
        // prints init to console
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //init motors
        InitializeMotors();
        //waits for button to be pressed
        waitForStart();
        //resets the runtime (no real value except for bug fixing)
        runtime.reset();

        //run class
        while (opModeIsActive()) {

            telemetry.update();
            //initiates an instance of the DriveTrain class, which contains all of the code for robot movement
            Drive DriveTrain = new Drive(robot) ;

            //sets the variables to the cordinates of the joy-stick

            double y = (-gamepad1.left_stick_y);
            double x = (gamepad1.left_stick_x * 1.1);
            double rx = (gamepad1.right_stick_x);

            //calculates the values of the motors based on the joy-stick values
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx)  / denominator;
            double backLeftPower = (y - x + rx)  / denominator;
            double frontRightPower = (y - x - rx)  / denominator;
            double backRightPower = (y + x - rx)  / denominator;

            //runs the drive, powering the motors
            DriveTrain.PowerDrive((frontLeftPower), (frontRightPower), (backRightPower), (backLeftPower));


            //writes info to console(only for bug-fixing)
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "Back Left (%.2f), Back Right (%.2f), Front Left (%.2f), Front Right (%.2f)" , backLeftPower, backRightPower, frontLeftPower, frontRightPower);
            telemetry.update();

        }
    }

    public void InitializeMotors(){
        robot.init(hardwareMap);
}

}
