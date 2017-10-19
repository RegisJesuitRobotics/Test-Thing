package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;

public class DriveCode {
	CANTalon L1, L2, L3, R1, R2, R3, TurningMotor, ElevationMotorLeft, ElevationMotorRight;
	controller playStation;
	DriverStation driverStation;
	double timeBetweenR1Presses = 10;

	double timeBetweenR1PResses2 = 10;
	double Limiter;

	int inputDelay = 2500;
	double oversample, timeBetweenPresses;

	public DriveCode(controller playStation) {
		L1 = new CANTalon(0);
		L2 = new CANTalon(1);
		R1 = new CANTalon(3);
		R2 = new CANTalon(2);
		// CHECK THESE CANTALONS FOR PORTS

		playStation = new controller(0);

		// Code Stuff
		this.playStation = playStation;
		Limiter = 0.3;

		// TIMER STUFF
		timeBetweenPresses = 300;
		oversample = 50;

	}

	public void DriveCodeMeth() {
		double RightTrigger = playStation.RightTrigger();
		double LeftTrigger = playStation.LeftTrigger();
		double LeftStick = playStation.LeftStickXAxis();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = RightTrigger - LeftTrigger;
		if (LeftStick > Deadzone) {

			RightPower = Power - (turn * Power);
			LeftPower = Power;
		} else if (LeftStick < -Deadzone) {

			LeftPower = Power + (turn * Power);
			RightPower = Power;
		} else {
			LeftPower = Power;
			RightPower = Power;
		}
		if (playStation.ButtonOptions() == true) {
			FASTButton();
		}
		R1.set(-RightPower * Limiter);
		R2.set(-RightPower * Limiter);

		L1.set(LeftPower * Limiter);
		L2.set(LeftPower * Limiter);

		// System.out.println(Limiter);

	}

	public void FASTButton() {
		if (playStation.ButtonOptions() == true) {

			if (Limiter == 1) {
				Limiter = 0.3;
			} else if (Limiter == 0.3) {
				Limiter = 1;

			}
		}

	}

	// AUTOnOMOUS COMMANDS

}
