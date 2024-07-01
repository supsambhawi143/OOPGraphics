package test;
import uk.ac.leedsbeckett.oop.OOPGraphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class LBU extends OOPGraphics {

public static void main(String[] args) {

new LBU();

}

public LBU() {

JFrame MainFrame = new JFrame(); //create a frame to display the turtle panel on

MainFrame.setLayout(new FlowLayout()); //not strictly necessary

MainFrame.add(this); //"this" is this object that extends turtle graphics, so we are adding a turtle graphics panel to the frame

MainFrame.pack(); //set the frame to a size we can see

MainFrame.setVisible(true);

displayMessage("This is my Project");//now display it

//about();

}

String command_file ="command.txt";

@Override 

public void processCommand(String s) {
	getGraphicsContext().drawString("Sambhawi Paudel"+ " "+ "", 250, 300);

if (!s.equals("Load Commands")) {

try (FileWriter file = new FileWriter(command_file, true)) {

file.write(s + System.lineSeparator());

} catch (Exception e) {

System.out.println("Error appending commands to file. Try Again. Error : " + e);

return;

}

}

if (!s.equals("")) {

if (s.equals("exit")) {

boolean val = chk_save();

if (val){

System.exit(1);

}

}

if(s.equals("about")){

about();

return;

}

if (s.equals("reset")) {

hard_reset();

return;

}

if (s.equals("clear")) {

clear_screen();

return;

}

if (s.equals("pendown")) {

pen_down();

return;

}

if (s.equals("penup")) {

pen_up();

return;

}

if (s.equals("black")) {

pen_colour(Color.black);

return;

}

if (s.equals("green")) {

pen_colour(Color.green);

return;

}

if (s.equals("red")) {

pen_colour(Color.red);

return;

}

if (s.equals("white")) {

pen_colour(Color.white);

return;

}

if(s.equals("Load Commands")){

load_command();

return;

}

String[] lst1 = s.split(" ");

String cmd = lst1[0];

if (cmd.equals("Save")) {

String name = lst1[1];

save_image(name);

return;

}

if (cmd.equals("Load")) {

String name = lst1[1];

boolean chk = chk_save();

if (!chk) {

String message = "Do you want to save the current image?";

int a = JOptionPane.showConfirmDialog(null, message, "Image not saved!", JOptionPane.YES_NO_CANCEL_OPTION);

if(a==0){

String save = "Save "+ JOptionPane.showInputDialog("Enter the name file : ");

processCommand(save);

load_image(name);

return;

}

if(a==1){

load_image(name);

return;

}

if(a==2 || a==-1){

return;

}

}

load_image(name);

return;

}

try {

String[] lst2 = lst1[1].split(",");

int p1 = Integer.parseInt(lst2[0]);

int p2 = Integer.parseInt(lst2[1]);

int p3 = Integer.parseInt(lst2[2]);

if (p1 < 0 || p2 < 0 || p3 < 0) {

System.out.println("Negative Parameter detected. Try Again.");

return;

}

switch (cmd) {

case "bg"->{

set_background(p1,p2,p3);

}

case "pencolour" -> {

pen_colour(p1, p2, p3);

}

case "triangle" -> {

make_triangle(p1, p2, p3);

}

}

} catch (Exception ignore1) {

try {

int p1 = Integer.parseInt(lst1[1]);

if (p1 < 0) {

System.out.println("Negative Parameter detected. Try Again.");

return;

}

switch (cmd) {

case "circle" -> {

make_circle(p1);

}

case "turnleft" -> {

turn_left(p1);

}

case "turnright" -> {

turn_right(p1);

}

case "forward" -> {

go_forward(p1);

}

case "backward" -> {

go_backward(p1);

}

case "penwidth" -> {

pen_size(p1);

}

case "square" -> {

make_square(p1);

}

case "triangle" -> {

make_triangle(p1);

}

default -> System.out.println("Invalid Command. Try Again.");

}

} catch (Exception ignore2) {

if (cmd.equals("pencolour") || cmd.equals("bg")) {

System.out.println("Invalid parameters to " + cmd);

return;

}

List<String> data = Arrays.asList("circle", "turnleft", "turnright", "forward", "backward", "triangle");

if (data.contains(cmd)) {

System.out.println("Non-numeric Parameter. Try Again.");

} else {

System.out.println("Invalid Command. Try Again.");

}

}

}

} else {

String message = "Please input something and try again!";

JOptionPane.showMessageDialog(null, message, "No Input!", JOptionPane.WARNING_MESSAGE);

}

}

public void make_square(int a) {

penUp();

forward(a / 2);

penDown();

turnRight();

forward(a / 2);

turnRight();

forward(a);

turnRight();

forward(a);

turnRight();

forward(a);

turnRight();

forward(a / 2);

reset();

System.out.println("Making a square of side :" + a);

}

public void make_triangle(int a) {

int h = (int) ((a * Math.sqrt(3)) / 2);

penUp();

forward(h / 2);

penDown();

turnRight();

forward(a / 2);

turnRight(120);

forward(a);

turnRight(120);

forward(a);

turnRight(120);

forward(a / 2);

reset();

System.out.println("Making a triangle of side : " + a);

}

public void make_triangle(int a, int b, int c) {

double angleA = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2.0 * b * c)));

double angleB = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2.0 * a * c)));

double angleC = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2.0 * a * b)));

double s = (a + b + c) / 2.0;

double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

double h = (2 * area) / a;

penUp();

forward((int) (h / 2));

penDown();

turnLeft();

forward(a / 2);

turnLeft((int) (180 - angleC));

forward(b);

turnLeft((int) (180 - angleA));

forward(c);

turnLeft((int) (180 - angleB));

forward(a);

reset();

System.out.println("Making triangle of side " + a + ", " + b + " and " + c);

}

public void pen_size(int a) {

penSize = a;

}

public void pen_colour(Color name) {

setPenColour(name);

System.out.println("Changing pen-colour to : " + getPenColour());

}

public void pen_colour(int a, int b, int c) {

Color temp_color = new Color(a, b, c);

setPenColour(temp_color);

System.out.println("Changing pen-colour to " + getPenColour());

}

public void hard_reset() {

reset();

penDown();

penSize = 1;

setPenColour(Color.RED);

System.out.println("Reset Pressed");

}

public void save_image(String name) {

BufferedImage bufImg = getBufferedImage();

File output = new File(" " + name + ".png");

try {

ImageIO.write(bufImg, "png", output);

JOptionPane.showMessageDialog(null,"Image Saved!");

} catch (IOException e) {

System.out.println("Cannot save file named " + name + ".png.\nError : " + e);

}

}

public void load_image(String name) {

try {

File file = new File("Image" + name + ".png");

BufferedImage image = ImageIO.read(file);

setBufferedImage(image);

System.out.println("Loading the image : " + name);

} catch (IOException e) {

System.out.println("Cannot load file named " + name + ".png.\nError : " + e);

}

}

public void clear_screen() {

clear();

System.out.println("Clear Pressed ! commmand entered successfully");

}

public void pen_down() {

penDown();

System.out.println("Pen Down ! commmand entered successfully");

}

public void pen_up() {

penUp();

System.out.println("Pen Up ! commmand entered successfully");

}

public void make_circle(int a) {

circle(a);

System.out.println("Creating a circle with radius : " + a);

}

public void turn_left(int a) {

turnLeft(a);

System.out.println("Turning Left on " + a + " degree.");

}

public void turn_right(int a) {

turnRight(a);

System.out.println("Turning Right on " + a + " degree.");

}

public void go_forward(int a) {

forward(a);

System.out.println("Moving " + a + " pixels forward.");

}

public void go_backward(int a) {

forward(-a);

System.out.println("Moving " + a + " pixels backward.");

}

public void load_command(){

String fileName = " command.txt";

try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

String line;

while ((line = reader.readLine()) != null) {

processCommand(line);

}

} catch (IOException e) {

System.err.println("An error occurred while reading the file: " + e.getMessage());

}

}

public boolean chk_save(){

try (BufferedReader reader = new BufferedReader(new FileReader(command_file))) {

String line;

int i = 0;

String[] arr = new String[100];

while ((line = reader.readLine()) != null) {

arr[i] = line;

i = i + 1;

}

String val = arr[i - 2];

int space_index = val.indexOf(" ");

if (space_index != -1) {

String save = val.substring(0, space_index);

if (save.equals("Save"))

return true;

}

} catch (IOException e) {

String message="Can't Open the file";

JOptionPane.showMessageDialog(null,message,"",JOptionPane.ERROR_MESSAGE);

}

return false;

}

public void set_background(int a, int b, int c){

Color col = new Color(a,b,c);

setBackground_Col(col);

clear();

}

}


	