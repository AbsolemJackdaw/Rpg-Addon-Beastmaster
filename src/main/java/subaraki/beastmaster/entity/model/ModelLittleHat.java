package subaraki.beastmaster.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLittleHat extends ModelBase{

	private ModelRenderer base;
	private ModelRenderer hat;
	private ModelRenderer hatstring;

	public ModelLittleHat() {
		textureHeight = 16;
		textureWidth = 16;
		
		hat = new ModelRenderer(this, 0, 0);
		hat.addBox(0, 0, 0, 4, 6, 4);
		hat.setRotationPoint(-2, 0, -2);
		hat.mirror = true;
		
		hatstring = new ModelRenderer(this, 0, 0);
		hatstring.addBox(-0.2f, 1, -0.2f, 4, 1, 4);
		hatstring.setRotationPoint(-1.8f, 0, -1.8f);
		hatstring.mirror = true;
		
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-1, 0, -1, 6, 1, 6);
		base.setRotationPoint(-2, 0, -2);
		base.mirror = true;
		
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}
	
	public void render(float scale){
		hat.render(scale);
		base.render(scale);
	}
	
	public void renderString(float scale){
		hatstring.render(scale+0.005f);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
	}
}
