package Scene;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.util.GLState;

import Base.BaseScene;
import Manager.SceneManager;
import Manager.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {
	
	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_EXIT = 2;

	private void createMenuChildScene()
	{
		menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(0, 0);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.play_region, vbom), 1.2f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.options_region, vbom), 1.2f, 1);
	    final IMenuItem exitMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_EXIT, resourcesManager.exit_region, vbom), 2.8f, 2);
	    
	    menuChildScene.addMenuItem(playMenuItem);
	    menuChildScene.addMenuItem(optionsMenuItem);
	    menuChildScene.addMenuItem(exitMenuItem);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 10);
	    optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() - 110);
	    exitMenuItem.setPosition(exitMenuItem.getX()+ 350, exitMenuItem.getY()+ 150);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(menuChildScene);
	}

	@Override
	public void createScene()
	{
	     createBackground();
	     createMenuChildScene();
	}

	

	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
	{
	        switch(pMenuItem.getID())
	        {
	        case MENU_PLAY:
	            //Load Game Scene!
	            SceneManager.getInstance().loadGameScene(engine);
	            return true;
	        case MENU_OPTIONS:
	            return true;
	        case MENU_EXIT:
	            return true;
	        default:
	            return false;
	    }
	}
	
	@Override
	public void onBackKeyPressed()
	{
	    System.exit(0);
	}

	@Override
	public SceneType getSceneType()
	{
	    return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}
	
	private void createBackground()
	{
	    attachChild(new Sprite(960, 540, resourcesManager.menu_background_region, vbom)
	    {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) 
	        {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}

}
