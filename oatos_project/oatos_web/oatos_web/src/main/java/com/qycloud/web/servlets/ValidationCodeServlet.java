package com.qycloud.web.servlets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.background.BackgroundFactory;
import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.color.RandomColorFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.word.RandomWordFactory;

/**  
 * 验证码生成类  
 *  
 * 使用开源验证码项目patchca生成  
 * 依赖jar包：patchca-0.5.0.jar  
 * 项目网址：https://code.google.com/p/patchca/  
 */
public class ValidationCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 5126616339795936447L;

	private ConfigurableCaptchaService ccs = null;
	private static TextRenderer tr = null;
	private ColorFactory colorFactory = null;
	private RandomFontFactory fontFactory = null;
	private RandomWordFactory wordFactory = null;
	private TextRenderer textRenderer = null;
//	private static CurvesRippleFilterFactory crff = null; //干扰线波纹  
//	private static MarbleRippleFilterFactory mrff = null; //双波纹  
//	private static DoubleRippleFilterFactory drff = null; //
	private static WobbleRippleFilterFactory wrff = null; //摆波纹  
//	private static DiffuseRippleFilterFactory dirff = null; //大理石波纹  

	public ValidationCodeServlet() {
		super();
	}

	/** 
	 * Servlet销毁方法,负责销毁所使用资源. <br> 
	 */
	public void destroy() {
		wordFactory = null;
		colorFactory = null;
		fontFactory = null;
		textRenderer = null;
		ccs = null;
		super.destroy(); // Just puts "destroy" string in log  
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");

		HttpSession session = request.getSession(true);
		OutputStream outputStream = response.getOutputStream();

		ccs.setFilterFactory(wrff);
		/*switch (r.nextInt(5)) {
		case 0:
			ccs.setFilterFactory(crff);
			break;
		case 1:
			ccs.setFilterFactory(mrff);
			break;
		case 2:
			ccs.setFilterFactory(drff);
			break;
		case 3:
			ccs.setFilterFactory(wrff);
			break;
		case 4:
			ccs.setFilterFactory(dirff);
			break;
		}*/

		// 得到验证码对象,有验证码图片和验证码字符串  
		Captcha captcha = ccs.getCaptcha();
		// 取得验证码字符串放入Session  
		String validationCode = captcha.getChallenge();
		session.setAttribute("validationCode", validationCode);
		// 取得验证码图片并输出  
		BufferedImage bufferedImage = captcha.getImage();
		ImageIO.write(bufferedImage, "png", outputStream);

		outputStream.flush();
		outputStream.close();
	}

	/** 
	 * Servlet初始化方法 
	 */
	public void init() throws ServletException {
		ccs = new ConfigurableCaptchaService();

		// 颜色创建工厂,使用一定范围内的随机色  
		colorFactory = new RandomColorFactory();
		ccs.setColorFactory(colorFactory);

		tr = new BestFitTextRenderer();
		ccs.setTextRenderer(tr);

		// 随机字体生成器  
		fontFactory = new RandomFontFactory();
		fontFactory.setMaxSize(32);
		fontFactory.setMinSize(28);
		ccs.setFontFactory(fontFactory);

		// 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等  
		wordFactory = new RandomWordFactory();
		wordFactory.setCharacters("abcdefhkmnstwxz23456789");
		wordFactory.setMaxLength(5);
		wordFactory.setMinLength(4);
		ccs.setWordFactory(wordFactory);

		// 自定义验证码图片背景  
		SingleColorBackgroundFactory backgroundFactory = new SingleColorBackgroundFactory();
		ccs.setBackgroundFactory(backgroundFactory);

//		// 图片滤镜设置  
//		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
//
//		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
//		WobbleImageOp wobbleImageOp = new WobbleImageOp();
//		wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
//		wobbleImageOp.setxAmplitude(2.0);
//		wobbleImageOp.setyAmplitude(1.0);
//		filters.add(wobbleImageOp);
//		filterFactory.setFilters(filters);
//
//		ccs.setFilterFactory(filterFactory);

		// 文字渲染器设置  
		textRenderer = new BestFitTextRenderer();
		textRenderer.setBottomMargin(3);
		textRenderer.setTopMargin(3);
		ccs.setTextRenderer(textRenderer);

		// 验证码图片的大小  
		ccs.setWidth(82);
		ccs.setHeight(32);

//		crff = new CurvesRippleFilterFactory(ccs.getColorFactory());
//		drff = new DoubleRippleFilterFactory();
//		dirff = new DiffuseRippleFilterFactory();
//		mrff = new MarbleRippleFilterFactory();
		wrff = new WobbleRippleFilterFactory();
	}

	/** 
	 * 自定义验证码图片背景,主要画一些噪点和干扰线 
	 */
	public class MyCustomBackgroundFactory implements BackgroundFactory {
		private Random random = new Random();

		public void fillBackground(BufferedImage image) {
			Graphics graphics = image.getGraphics();

			// 验证码图片的宽高  
			int imgWidth = image.getWidth();
			int imgHeight = image.getHeight();

			// 填充为白色背景  
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, imgWidth, imgHeight);

			// 画100个噪点(颜色及位置随机)  
			for (int i = 0; i < 100; i++) {
				// 随机颜色  
				int rInt = random.nextInt(255);
				int gInt = random.nextInt(255);
				int bInt = random.nextInt(255);

				graphics.setColor(new Color(rInt, gInt, bInt));

				// 随机位置  
				int xInt = random.nextInt(imgWidth - 3);
				int yInt = random.nextInt(imgHeight - 2);

				// 随机旋转角度  
				int sAngleInt = random.nextInt(360);
				int eAngleInt = random.nextInt(360);

				// 随机大小  
				int wInt = random.nextInt(6);
				int hInt = random.nextInt(6);

				graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);

				// 画5条干扰线  
				if (i % 20 == 0) {
					int xInt2 = random.nextInt(imgWidth);
					int yInt2 = random.nextInt(imgHeight);
					graphics.drawLine(xInt, yInt, xInt2, yInt2);
				}
			}
		}
	}
}