/*
 * Copyright 2019-2019 Michael Hoffer <info@michaelhoffer.de>. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * If you use this software for scientific research then please cite the following publication(s):
 *
 * M. Hoffer, C. Poliwoda, & G. Wittum. (2013). Visual reflection library:
 * a framework for declarative GUI programming on the Java platform.
 * Computing and Visualization in Science, 2013, 16(4),
 * 181–192. http://doi.org/10.1007/s00791-014-0230-y
 */
package eu.mihosoft.nativefx;

import java.nio.ByteBuffer;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NativeFXApp extends Application {

    public void start(Stage primaryStage) {

        VBox root = new VBox();
        
        TextField tf = new TextField("_mem_0");
        Button btn = new Button("Connect");
        Button delBtn = new Button("Delete All");

        delBtn.setOnAction((ae)-> {
            root.getChildren().filtered(n->n instanceof NativeNode).
            forEach(n->{
                NativeNode nn = (NativeNode) n;
                nn.disconnect();
            });
            root.getChildren().removeIf(n->n instanceof NativeNode);
        });

        btn.setOnAction((ae)-> {
            NativeNode nativeN = new NativeNode();
            VBox.setVgrow(nativeN,Priority.SOMETIMES);
            nativeN.connect(tf.getText());
            root.getChildren().add(nativeN);
        });

        ToolBar bar = new ToolBar(tf, btn, delBtn);
        root.getChildren().add(bar);

        Scene scene = new Scene(root, 1024,768);

        primaryStage.setTitle("NativeFX Test");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((value)->System.exit(0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

