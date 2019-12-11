package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestion.GestionImagen;
import model.ConvertirFoto;
import model.Imagenes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

// LIBRERIAS NECESARIAS
import java.io.*;
import javax.swing.JTextField;

public class JFIndex extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JScrollPane scpTable;
	private JTable tblListado;
	private JButton btnAbrir;
	private JTextField txtArchivo;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JLabel lblFoto;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFIndex frame = new JFIndex();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFIndex() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Cargar Ficheros");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(111, 11, 135, 24);
		contentPane.add(lblTitulo);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscarAchivo();
				
			}
		});
		btnAbrir.setBounds(10, 39, 89, 23);
		contentPane.add(btnAbrir);
		
		scpTable = new JScrollPane();
		scpTable.setBounds(10, 115, 496, 211);
		contentPane.add(scpTable);
		
		tblListado = new JTable();
		scpTable.setViewportView(tblListado);
		
		txtArchivo = new JTextField();
		txtArchivo.setEditable(false);
		txtArchivo.setBounds(101, 39, 247, 22);
		contentPane.add(txtArchivo);
		txtArchivo.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 82, 57, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(76, 84, 272, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblFoto = new JLabel("FOTO");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(370, 11, 136, 93);
		contentPane.add(lblFoto);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarImagen();
			}
		});
		btnGuardar.setBounds(195, 337, 89, 23);
		contentPane.add(btnGuardar);
		
		
	}
	
	public void buscarAchivo() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Buscar Imagen");
		int x = fileChooser.showOpenDialog(null);
		
		if(x == 0) {
			try {
				
				String archivo = fileChooser.getSelectedFile().getAbsolutePath();
				
				// SETTEO LA RUTA DEL ARCHIVO 
				txtArchivo.setText(archivo);

				// GUARDO EL ARCHIVO EN UN TIPO FILE
				File file = new File(archivo);
				
				// LEE EL ARCHIVO(IMAGEN)
				BufferedImage src = ImageIO.read(file);
				
				// LE DA UNA DIMENSIÓN A LA IMAGEN
				BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
				
				// AJUSTA LOS PIXELES AL TAMAÑO DEL JFRAME
				Graphics2D graphics = dest.createGraphics();
				
				// SE AJUSTA AL TAMAÑO DEL LABEL
				AffineTransform affineTransform = AffineTransform.getScaleInstance(src.getWidth()/src.getWidth(), src.getHeight()/src.getHeight());
				
				// SE RENDERIZA LA IMAGEN CONFORME EL LABEL
				graphics.drawRenderedImage(src, affineTransform);
				
				ImageIcon icon = new ImageIcon(archivo);
				icon = new ImageIcon(dest);
				
				lblFoto.setText("");
				lblFoto.setIcon(new ImageIcon(new ImageIcon(dest).getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT)));
				
				icon.getIconHeight();				
			} 
			catch (Exception e) {
				System.out.println("Error de imagen: " + e.getMessage());								
			}
		}
	
	}

	public void guardarImagen() {
		if(txtNombre.getText().isEmpty() || lblFoto.getText().equals("FOTO")) {
			JOptionPane.showMessageDialog(null, "Los Campos vacíos");
		}
		else {
			Imagenes img = new Imagenes();
			img.setNombre(txtNombre.getText());
			
			Image image = ConvertirFoto.iconToImage(lblFoto.getIcon());
			img.setImagen(ConvertirFoto.imageToByte(image));
			
			GestionImagen gi = new GestionImagen();
			
			int registro = gi.InsertarImagen(img);
			
			if(registro > 0) {
				JOptionPane.showMessageDialog(this, "Se guardo correctamente");
			}
			
			txtNombre.setText("");
		}
	}
}
