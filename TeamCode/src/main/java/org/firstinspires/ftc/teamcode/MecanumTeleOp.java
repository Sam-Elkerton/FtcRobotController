package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

    @TeleOp
    public class MecanumTeleOp extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            // Declare our motors
            // Make sure your ID's match your configuration
            DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
            DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
            DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
            DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
            waitForStart();

            motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);

            while (opModeIsActive()) {
                if(isStopRequested() == true){
                    break;
                }else{
                    double y = -gamepad1.left_stick_y; // Remember, this is reversed!
                    double x = gamepad1.left_stick_x;
                    double rx = gamepad1.right_stick_x;

                    motorFrontLeft.setPower(y + x + rx);
                    motorBackLeft.setPower(y - x + rx);
                    motorFrontRight.setPower(y - x - rx);
                    motorBackRight.setPower(y + x - rx);
                }
            }
        }
    }