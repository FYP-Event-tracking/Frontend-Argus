package projectargus;
import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URI;
import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.util.Base64;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeHome extends javax.swing.JFrame {
    private User user;
    private String userID;
    Webcam webcam;
    Boolean isRunning = false;
    private String websocketEndpoint = "ws://localhost:8009/";
    private WebSocketClient webSocketClient;
    private VideoFeedTaker videoFeedTaker;
    private File selectedFile;
    
    public EmployeeHome() {
        initComponents();
//        loadConfig();
    }

    public EmployeeHome(User user) {
        this.user = user;
        initComponents();
//        loadConfig();
        List<Webcam> webcams = Webcam.getWebcams();
        if (webcams.isEmpty()) {
            UserMsgLable.setText("No webcams found.");
        } else {
        String[] webcamNames = new String[webcams.size()];
        for (int i = 0; i < webcams.size(); i++) {
            webcamNames[i] = webcams.get(i).getName();
        }

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(webcamNames);
        CamComboBox.setModel(comboBoxModel);
        CamComboBox.setSelectedIndex(0);
        
         CamComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCameraName = (String) CamComboBox.getSelectedItem();
                    for (Webcam cam : webcams) {
                        if (cam.getName().equals(selectedCameraName)) {
                            if (webcam != null && webcam.isOpen()) {
                                webcam.close();
                            }
                            webcam = cam;
                            webcam.setViewSize(new Dimension(640, 480));
                            break;
                        }
                    }
                }
            });
        }
        
        if (!webcams.isEmpty()) {
            webcam = Webcam.getDefault();
            webcam.setViewSize(new Dimension(640, 480));
        }
        
        UserNameLable.setText(user.getUserName());
        UserPositionLable.setText(user.getUserType());
        UserIdLable.setText(user.getUserId());
        userID = user.getUserId();
    }
    
//    private void loadConfig() {
//        Properties prop = new Properties();
//        try (FileInputStream input = new FileInputStream("config.properties")) {
//            prop.load(input);
//            websocketEndpoint = prop.getProperty("websocketEndpoint");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    private void stopWebcam() {
        if (isRunning) {
            isRunning = false;
            webcam.close();
            Camfeed.setIcon(new ImageIcon());
            if (videoFeedTaker != null) {
                videoFeedTaker.stopRunning();
            }
            if (webSocketClient != null) {
                webSocketClient.close();
            }        
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        UserNameLable = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        UserPositionLable = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        UserIdLable = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        BoxIdTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ItemTypeTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        LogIdTextField = new javax.swing.JTextField();
        Strat = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        UserMsgLable = new javax.swing.JLabel();
        UploadVideoButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        CamComboBox = new javax.swing.JComboBox<>();
        Camfeed = new javax.swing.JLabel();
        LogOutLable = new javax.swing.JLabel();
        ContinuosButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project Argus");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(201, 34, 42));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 600));

        jPanel3.setPreferredSize(new java.awt.Dimension(1088, 586));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectargus/Logo-Argus 80x80.png"))); // NOI18N
        jLabel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Employee Name :");

        UserNameLable.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        UserNameLable.setText("Hansa Hettiarachchi");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Position :");

        UserPositionLable.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        UserPositionLable.setText("Employee");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Employee ID :");

        UserIdLable.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        UserIdLable.setText("emp123");

        jPanel4.setPreferredSize(new java.awt.Dimension(1076, 486));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Box ID : ");

        BoxIdTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Item Type :");

        ItemTypeTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Log ID :");

        LogIdTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        Strat.setBackground(new java.awt.Color(0, 255, 0));
        Strat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Strat.setText("START");
        Strat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StratActionPerformed(evt);
            }
        });

        Stop.setBackground(new java.awt.Color(255, 0, 0));
        Stop.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Stop.setText("STOP");
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });

        UserMsgLable.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        UserMsgLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        UploadVideoButton.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        UploadVideoButton.setText("Upload Video");
        UploadVideoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadVideoButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("OR");

        CamComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(Strat, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(193, 193, 193))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(LogIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(ItemTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(BoxIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(UploadVideoButton))
                            .addComponent(UserMsgLable, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(CamComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoxIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ItemTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CamComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(UserMsgLable)
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Strat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(UploadVideoButton)
                .addGap(54, 54, 54))
        );

        Camfeed.setBackground(new java.awt.Color(204, 255, 255));
        Camfeed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Camfeed.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Camfeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Camfeed, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        LogOutLable.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        LogOutLable.setForeground(new java.awt.Color(0, 0, 204));
        LogOutLable.setText("Log Out");
        LogOutLable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOutLableMouseClicked(evt);
            }
        });

        ContinuosButton.setText("Continuos monitoring");
        ContinuosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinuosButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(UserNameLable))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(UserPositionLable)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LogOutLable))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UserIdLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ContinuosButton))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(UserNameLable))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(UserPositionLable)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(LogOutLable)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(UserIdLable)
                            .addComponent(ContinuosButton))))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StratActionPerformed
        String logId = (String) LogIdTextField.getText();
        String boxId = (String) BoxIdTextField.getText();
        String itemType = (String) ItemTypeTextField.getText();
        
        if(logId.isEmpty() || boxId.isEmpty() || itemType.isEmpty()) {
            UserMsgLable.setText("Error : Input all details to start");
        } else {
            UserMsgLable.setText("");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String startTime = now.format(formatter);
            
            startWebcamWithData(logId, boxId, itemType, startTime);
        }
    }//GEN-LAST:event_StratActionPerformed

    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        stopWebcam();    
    }//GEN-LAST:event_StopActionPerformed

    private void LogOutLableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutLableMouseClicked
        Login login = new Login();
        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_LogOutLableMouseClicked

    private void UploadVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadVideoButtonActionPerformed
        String logId = (String) LogIdTextField.getText();
        String boxId = (String) BoxIdTextField.getText();
        String itemType = (String) ItemTypeTextField.getText();

        if (logId.isEmpty() || boxId.isEmpty() || itemType.isEmpty()) {
            UserMsgLable.setText("Error: Input all details to send video");
        } else {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".mp4");
                }

                @Override
                public String getDescription() {
                    return "MP4 Videos (*.mp4)";
                }
            });

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                UserMsgLable.setText("Processing....");
                
                String response = uploadVideoFile(selectedFile, logId, boxId, itemType);

                if (response.equals("200")) {
                    UserMsgLable.setText("Processing Complete");
                } else {
                    UserMsgLable.setText("Error: " + response);
                }

            } else {
                UserMsgLable.setText("No Video file selected");
            }
        }
    }//GEN-LAST:event_UploadVideoButtonActionPerformed

    private void ContinuosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinuosButtonActionPerformed
        EmployeeHomeContinuos employeeHomeContinuos = new EmployeeHomeContinuos(user);
        employeeHomeContinuos.setVisible(true);
        employeeHomeContinuos.pack();
        employeeHomeContinuos.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_ContinuosButtonActionPerformed

    private String uploadVideoFile(File videoFile, String logId, String boxId, String itemType) {
        String urlString = "http://localhost:8011/submit";
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just a random string
        String charset = "UTF-8";
        String CRLF = "\r\n"; // Line separator required by multipart/form-data

        HttpURLConnection connection = null;
        OutputStream output = null;
        PrintWriter writer = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("User-Agent", "Java client");

            output = connection.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(output, charset), true);

            // Send normal parameters
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"logId\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(logId).append(CRLF).flush();

            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"boxId\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(boxId).append(CRLF).flush();

            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"itemType\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(itemType).append(CRLF).flush();
            
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"userId\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(userID).append(CRLF).flush();

            // Send binary file
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + videoFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: video/mp4").append(CRLF);
            writer.append(CRLF).flush();
            FileInputStream inputStream = new FileInputStream(videoFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            output.flush(); // Important before continuing with writer!
            inputStream.close();
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();

            // Check the server's response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "200";
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (writer != null) writer.close();
            if (output != null) try { output.close(); } catch (IOException e) { e.printStackTrace(); }
            if (connection != null) connection.disconnect();
        }
    }
    
    private void startWebcamWithData(String logId, String boxId, String itemType, String startTime) {
        if(!isRunning){
            isRunning = true;
            webcam.open();
            new VideoFeedTaker(logId, boxId, itemType, startTime).start();
        }
    }
    
    class VideoFeedTaker extends Thread {
        private String logId, boxId, itemType, startTime;
        private boolean running = true;

        public VideoFeedTaker(String logId, String boxId, String itemType, String startTime) {
            this.logId = logId;
            this.boxId = boxId;
            this.itemType = itemType;
            this.startTime = startTime;
        }

        @Override
        public void run() {
            try {
                webSocketClient = new WebSocketClient(new URI(websocketEndpoint)) {
                    @Override
                    public void onOpen(ServerHandshake handshake) {
                        System.out.println("WebSocket connection opened");
                    }

                    @Override
                    public void onMessage(String message) {
                        System.out.println("Received message: " + message);
                    }

                    @Override
                    public void onClose(int code, String reason, boolean remote) {
                        System.out.println("WebSocket connection closed: " + reason);
                    }

                    @Override
                    public void onError(Exception ex) {
                        System.out.println("WebSocket error: " + ex.getMessage());
                    }
                };
                webSocketClient.connectBlocking();

                while (isRunning) {
                    Image image = webcam.getImage();
                    Camfeed.setIcon(new ImageIcon(image));

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write((java.awt.image.RenderedImage) image, "jpg", baos);
                    byte[] imageBytes = baos.toByteArray();

                    sendFrameWithMetadata(imageBytes);

                    Thread.sleep(100);
                }
            } catch (Exception ex) {
                Logger.getLogger(EmployeeHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void sendFrameWithMetadata(byte[] imageBytes) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                baos.write(("LogId:" + logId + "\n").getBytes());
                baos.write(("BoxId:" + boxId + "\n").getBytes());
                baos.write(("ItemType:" + itemType + "\n").getBytes());
                baos.write(("UserId:" + userID + "\n").getBytes());
                baos.write(("StartTime:" + startTime + "\n").getBytes());
                String imageByteString = Base64.getEncoder().encodeToString(imageBytes);
                baos.write(("ImageData:" + imageByteString + "\n").getBytes());
                webSocketClient.send(ByteBuffer.wrap(baos.toByteArray()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
        
        public void stopRunning() {
            running = false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BoxIdTextField;
    private javax.swing.JComboBox<String> CamComboBox;
    private javax.swing.JLabel Camfeed;
    private javax.swing.JButton ContinuosButton;
    private javax.swing.JTextField ItemTypeTextField;
    private javax.swing.JTextField LogIdTextField;
    private javax.swing.JLabel LogOutLable;
    private javax.swing.JButton Stop;
    private javax.swing.JButton Strat;
    private javax.swing.JButton UploadVideoButton;
    private javax.swing.JLabel UserIdLable;
    private javax.swing.JLabel UserMsgLable;
    private javax.swing.JLabel UserNameLable;
    private javax.swing.JLabel UserPositionLable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
