//package com.example.pixaloop.features;
//
//import android.content.Context;
//import android.content.res.Resources;
//import androidx.annotation.ColorRes;
//import androidx.annotation.Nullable;
//import com.google.common.base.Preconditions;
//import com.google.common.collect.BiMap;
//import com.google.common.collect.ImmutableBiMap;
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableMap;
//import com.google.common.collect.ImmutableSet;
//import com.lightricks.pixaloop.R;
//import com.lightricks.pixaloop.features.AnimateModel;
//import com.lightricks.pixaloop.features.CameraFxModel;
//import com.lightricks.pixaloop.features.FeatureItem;
//import com.lightricks.pixaloop.features.SessionState;
//import com.lightricks.pixaloop.features.SkyModel;
//import com.lightricks.pixaloop.remoteResources.RemoteAssetsManager;
//import com.lightricks.pixaloop.remoteResources.RemoteFeatureType;
//import com.lightricks.pixaloop.remoteResources.model.AssetDescriptor;
//import com.lightricks.pixaloop.remoteResources.model.PackDescriptor;
//import com.lightricks.pixaloop.toolbar.ToolbarItemStyle;
//import com.lightricks.pixaloop.util.TreeNode;
//import com.lightricks.pixaloop.util.UriUtils;
//import io.reactivex.Observable;
//import io.reactivex.Single;
//import io.reactivex.SingleEmitter;
//import io.reactivex.subjects.BehaviorSubject;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//import java.util.TreeSet;
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//@Singleton
//public class FeatureItemsRepository {
//    public static final int[] a = {R.color.pnx_secondary_orange, R.color.pnx_secondary_orange2, R.color.pnx_secondary_orange3};
//    public static final BiMap<AnimateModel.MotionType, String> b = ImmutableBiMap.b().a(AnimateModel.MotionType.SEAMLESS_LOOP, "animate_loop_blend").a(AnimateModel.MotionType.BOUNCE, "animate_loop_boomerang").a(AnimateModel.MotionType.LOOP, "animate_loop_circular").a();
//    public static final Map<AnimateModel.MotionType, Integer> c = ImmutableMap.b().a(AnimateModel.MotionType.SEAMLESS_LOOP, Integer.valueOf(R.string.caption_animate_loop_blend)).a(AnimateModel.MotionType.BOUNCE, Integer.valueOf(R.string.caption_animate_loop_boomerang)).a(AnimateModel.MotionType.LOOP, Integer.valueOf(R.string.caption_animate_loop_circular)).a();
//    public final BehaviorSubject<TreeNode<FeatureItem>> d = BehaviorSubject.n();
//    public final ProFeaturesConfiguration e;
//    public final RemoteAssetsManager f;
//    public final Context g;
//
//    @Inject
//    public FeatureItemsRepository(ProFeaturesConfiguration proFeaturesConfiguration, RemoteAssetsManager remoteAssetsManager, Context context) {
//        this.e = proFeaturesConfiguration;
//        this.f = remoteAssetsManager;
//        this.g = context;
//        a((Map<RemoteFeatureType, List<PackDescriptor>>) new HashMap());
//    }
//
//    public final List<TreeNode<FeatureItem>> c(final Context context) {
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_path").b(context.getString(R.string.animate_path)).b(Integer.valueOf(R.drawable.ic_animate_path)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(this.e.j()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_anchor").b(context.getString(R.string.animate_anchor)).b(Integer.valueOf(R.drawable.ic_animate_anchor)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(this.e.g()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_geometric").b(context.getString(R.string.animate_geometric)).b(Integer.valueOf(R.drawable.ic_animate_geometric)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(this.e.h()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_remove").b(context.getString(R.string.animate_remove)).b(Integer.valueOf(R.drawable.ic_animate_remove)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_speed").b(context.getString(R.string.animate_speed)).b(Integer.valueOf(R.drawable.ic_animate_speed)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(this.e.k()).a(FeatureItem.FeatureItemSlider.a().b(0.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueProvider) C0082df.a).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.b().a(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_animate_speed, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.c().i()))}));
//            }
//        }).a()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_freeze").b(context.getString(R.string.animate_freeze)).b(Integer.valueOf(R.drawable.ic_animate_freeze)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_unfreeze").b(context.getString(R.string.animate_unfreeze)).b(Integer.valueOf(R.drawable.ic_animate_unfreeze)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_loop").b(context.getString(R.string.animate_loop)).b(Integer.valueOf(R.drawable.ic_animate_loop)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.TAP_TO_ENTER).a(c()).a(this.e.i()).a(), h(context)));
//        return arrayList;
//    }
//
//    public final TreeNode<FeatureItem> d(Context context) {
//        return new TreeNode<>(FeatureItem.a().a("animate").b(context.getString(R.string.animate)).b(Integer.valueOf(R.drawable.ic_animate)).a(ToolbarItemStyle.ICON).a(), c(context));
//    }
//
//    public TreeNode<FeatureItem> e() {
//        return this.d.o();
//    }
//
//    public Observable<TreeNode<FeatureItem>> f() {
//        return this.d;
//    }
//
//    public final TreeNode<FeatureItem> g(Context context) {
//        FeatureItem.ItemClickedHandler a2 = a("element_name_none");
//        FeatureItem.FeatureItemSlider f2 = f(context);
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(new TreeNode(FeatureItem.a().a("element_name_none").b(context.getString(R.string.none_item_title)).a(UriUtils.a(context, R.drawable.none)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(a2).a(FeatureItemsPackInfo.a().a((int) R.color.pnx_gray1).a()).a(), (List) null));
//        FeatureItemsPackInfo a3 = FeatureItemsPackInfo.a().a(context.getString(R.string.element_essential)).a((int) R.color.pnx_secondary_orange).a();
//        FeatureItem.Builder a4 = FeatureItem.a().a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(a2).a(f2);
//        arrayList.add(new TreeNode(a4.a("element_es01").b(context.getString(R.string.element_es01_title)).a(UriUtils.a(context, R.drawable.element_es01_tn)).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_es02").b(context.getString(R.string.element_es02_title)).a(UriUtils.a(context, R.drawable.element_es02_tn)).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_es03").b(context.getString(R.string.element_es03_title)).a(UriUtils.a(context, R.drawable.element_es03_tn)).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_es04").b(context.getString(R.string.element_es04_title)).a(UriUtils.a(context, R.drawable.element_es04_tn)).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_es05").b(context.getString(R.string.element_es05_title)).a(UriUtils.a(context, R.drawable.element_es05_tn)).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_es06").b(context.getString(R.string.element_es06_title)).a(UriUtils.a(context, R.drawable.element_es06_tn)).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_es07").b(context.getString(R.string.element_es07_title)).a(UriUtils.a(context, R.drawable.element_es07_tn)).a(f2).a(a3).a(this.e.c().contains(a4.b())).a(), (List) null));
//        FeatureItemsPackInfo a5 = FeatureItemsPackInfo.a().a(context.getString(R.string.element_butterfly)).a((int) R.color.pnx_secondary_orange).a();
//        arrayList.add(new TreeNode(a4.a("element_bt01").b(context.getString(R.string.element_bt01_title)).a(UriUtils.a(context, R.drawable.element_bt01_tn)).a(a5).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_bt02").b(context.getString(R.string.element_bt02_title)).a(UriUtils.a(context, R.drawable.element_bt02_tn)).a(a5).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_bt03").b(context.getString(R.string.element_bt03_title)).a(UriUtils.a(context, R.drawable.element_bt03_tn)).a(a5).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_bt04").b(context.getString(R.string.element_bt04_title)).a(UriUtils.a(context, R.drawable.element_bt04_tn)).a(a5).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_bt05").b(context.getString(R.string.element_bt05_title)).a(UriUtils.a(context, R.drawable.element_bt05_tn)).a(a5).a(this.e.c().contains(a4.b())).a(), (List) null));
//        arrayList.add(new TreeNode(a4.a("element_bt06").b(context.getString(R.string.element_bt06_title)).a(UriUtils.a(context, R.drawable.element_bt06_tn)).a(a5).a(this.e.c().contains(a4.b())).a(), (List) null));
//        a((List<TreeNode<FeatureItem>>) arrayList, (Set<String>) this.e.c());
//        return new TreeNode<>(FeatureItem.a().a("element").b(context.getString(R.string.element)).b(Integer.valueOf(R.drawable.ic_element)).a(ToolbarItemStyle.ICON).a(Integer.valueOf(R.drawable.ic_badge_new)).a((FeatureItem.SelectedChildProvider) new FeatureItem.SelectedChildProvider("element_name_none") {
//            public String a(SessionState sessionState) {
//                ImmutableList<OverlayItemModel> b2 = sessionState.e().b();
//                if (b2.size() > 0) {
//                    return b2.get(b2.size() - 1).c();
//                }
//                return "element_name_none";
//            }
//        }).a(), arrayList);
//    }
//
//    public final List<TreeNode<FeatureItem>> h(Context context) {
//        ArrayList arrayList = new ArrayList();
//        FeatureItem.ItemClickedHandler i = i(context);
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_loop_blend").b(context.getString(R.string.animate_loop_blend)).b(Integer.valueOf(R.drawable.ic_animate_loop_blend)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(i).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_loop_boomerang").b(context.getString(R.string.animate_loop_boomerang)).b(Integer.valueOf(R.drawable.ic_animate_loop_boomerang)).a(ToolbarItemStyle.ICON).a(i).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("animate_loop_circular").b(context.getString(R.string.animate_loop_circular)).b(Integer.valueOf(R.drawable.ic_animate_loop_circular)).a(ToolbarItemStyle.ICON).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(i).a(), (List) null));
//        return arrayList;
//    }
//
//    public final FeatureItem.ItemClickedHandler i(final Context context) {
//        return new FeatureItem.ItemClickedHandler() {
//            @Nullable
//            public Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources) {
//                SessionState.Builder h = sessionState.h();
//                AnimateModel.MotionType motionType = (AnimateModel.MotionType) FeatureItemsRepository.b.a().get(str);
//                h.b().a(motionType);
//                return Single.a(Optional.of(SessionStep.a().a(h.c()).a(SessionStepCaption.a(context.getString(((Integer) FeatureItemsRepository.c.get(motionType)).intValue()))).a()));
//            }
//        };
//    }
//
//    public final TreeNode<FeatureItem> k(Context context) {
//        final TreeSet treeSet = new TreeSet();
//        treeSet.add("sky_essential_sn01");
//        treeSet.add("sky_essential_sn02");
//        treeSet.add("sky_essential_sn03");
//        treeSet.add("sky_blue_bl04");
//        treeSet.add("sky_blue_bl01");
//        treeSet.add("sky_blue_bl02");
//        treeSet.add("sky_blue_bl03");
//        AnonymousClass4 r8 = new FeatureItem.ItemClickedHandler() {
//            public Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources) {
//                if (str.equals(sessionState.g().e().c("sky_none"))) {
//                    return Single.a(Optional.empty());
//                }
//                SessionState.Builder h = sessionState.h();
//                SkyModel.Builder g = h.g();
//                g.a(!str.equals("sky_none") ? str : null);
//                if (treeSet.contains(str)) {
//                    g.a(0.0f);
//                } else {
//                    g.a(0.5f);
//                }
//                return Single.a(Optional.of(SessionStep.a().a(h.c()).a(SessionStepCaption.a(resources.getString(R.string.sky))).a()));
//            }
//        };
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(new TreeNode(FeatureItem.a().a("sky_none").b(context.getString(R.string.none_item_title)).a(UriUtils.a(context, R.drawable.none)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r8).a(FeatureItemsPackInfo.a().a((int) R.color.pnx_gray1).a()).a(), (List) null));
//        FeatureItem.Builder a2 = FeatureItem.a().b(Integer.valueOf(R.drawable.ic_more)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_AND_TAP_TO_ENTER).a((FeatureItem.ItemClickedHandler) r8);
//        FeatureItemsPackInfo a3 = FeatureItemsPackInfo.a().a(context.getString(R.string.essential)).a((int) R.color.pnx_main_orange).a();
//        arrayList.add(new TreeNode(a2.a("sky_blue_bl01").b(context.getString(R.string.sky_sn01_title)).a(UriUtils.a(context, R.drawable.sky_bl01_tn)).a(this.e.e().contains(a2.b())).a(a3).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_essential_sn01").b(context.getString(R.string.sky_sn02_title)).a(UriUtils.a(context, R.drawable.sky_sn01_tn)).a(this.e.e().contains(a2.b())).a(a3).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_essential_sn02").b(context.getString(R.string.sky_sn03_title)).a(UriUtils.a(context, R.drawable.sky_sn02_tn)).a(this.e.e().contains(a2.b())).a(a3).a(), a(context, a2.b())));
//        FeatureItemsPackInfo a4 = FeatureItemsPackInfo.a().a(context.getString(R.string.blue)).a((int) R.color.pnx_secondary_orange).a();
//        arrayList.add(new TreeNode(a2.a("sky_blue_bl02").b(context.getString(R.string.sky_bl01_title)).a(UriUtils.a(context, R.drawable.sky_bl02_tn)).a(this.e.e().contains(a2.b())).a(a4).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_blue_bl03").b(context.getString(R.string.sky_bl02_title)).a(UriUtils.a(context, R.drawable.sky_bl03_tn)).a(this.e.e().contains(a2.b())).a(a4).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_blue_bl04").b(context.getString(R.string.sky_bl03_title)).a(UriUtils.a(context, R.drawable.sky_bl04_tn)).a(this.e.e().contains(a2.b())).a(a4).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_essential_sn03").b(context.getString(R.string.sky_bl04_title)).a(UriUtils.a(context, R.drawable.sky_sn03_tn)).a(this.e.e().contains(a2.b())).a(a4).a(), a(context, a2.b())));
//        FeatureItemsPackInfo a5 = FeatureItemsPackInfo.a().a(context.getString(R.string.dusk)).a((int) R.color.pnx_secondary_orange2).a();
//        arrayList.add(new TreeNode(a2.a("sky_dusk_du01").b(context.getString(R.string.sky_du01_title)).a(UriUtils.a(context, R.drawable.sky_du01_tn)).a(this.e.e().contains(a2.b())).a(a5).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_dusk_du02").b(context.getString(R.string.sky_du02_title)).a(UriUtils.a(context, R.drawable.sky_du02_tn)).a(this.e.e().contains(a2.b())).a(a5).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_dusk_du03").b(context.getString(R.string.sky_du03_title)).a(UriUtils.a(context, R.drawable.sky_du03_tn)).a(this.e.e().contains(a2.b())).a(a5).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_dusk_du04").b(context.getString(R.string.sky_du04_title)).a(UriUtils.a(context, R.drawable.sky_du04_tn)).a(this.e.e().contains(a2.b())).a(a5).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_dusk_du05").b(context.getString(R.string.sky_du05_title)).a(UriUtils.a(context, R.drawable.sky_du05_tn)).a(this.e.e().contains(a2.b())).a(a5).a(), a(context, a2.b())));
//        FeatureItemsPackInfo a6 = FeatureItemsPackInfo.a().a(context.getString(R.string.aurora)).a((int) R.color.pnx_secondary_orange3).a();
//        arrayList.add(new TreeNode(a2.a("sky_aurora_au01").b(context.getString(R.string.sky_au01_title)).a(UriUtils.a(context, R.drawable.sky_au01_tn)).a(this.e.e().contains(a2.b())).a(a6).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_aurora_au02").b(context.getString(R.string.sky_au02_title)).a(UriUtils.a(context, R.drawable.sky_au02_tn)).a(this.e.e().contains(a2.b())).a(a6).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_aurora_au03").b(context.getString(R.string.sky_au03_title)).a(UriUtils.a(context, R.drawable.sky_au03_tn)).a(this.e.e().contains(a2.b())).a(a6).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_aurora_au04").b(context.getString(R.string.sky_au04_title)).a(UriUtils.a(context, R.drawable.sky_au04_tn)).a(this.e.e().contains(a2.b())).a(a6).a(), a(context, a2.b())));
//        FeatureItemsPackInfo a7 = FeatureItemsPackInfo.a().a(context.getString(R.string.fantasy)).a((int) R.color.pnx_main_orange).a();
//        arrayList.add(new TreeNode(a2.a("sky_fantasy_fa01").b(context.getString(R.string.sky_fa01tn_title)).a(UriUtils.a(context, R.drawable.sky_fa01_tn)).a(this.e.e().contains(a2.b())).a(a7).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_fantasy_fa02").b(context.getString(R.string.sky_fa02tn_title)).a(UriUtils.a(context, R.drawable.sky_fa02_tn)).a(this.e.e().contains(a2.b())).a(a7).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_fantasy_fa03").b(context.getString(R.string.sky_fa03tn_title)).a(UriUtils.a(context, R.drawable.sky_fa03_tn)).a(this.e.e().contains(a2.b())).a(a7).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_fantasy_fa04").b(context.getString(R.string.sky_fa04tn_title)).a(UriUtils.a(context, R.drawable.sky_au04_tn)).a(this.e.e().contains(a2.b())).a(a7).a(), a(context, a2.b())));
//        arrayList.add(new TreeNode(a2.a("sky_fantasy_fa05").b(context.getString(R.string.sky_fa05tn_title)).a(UriUtils.a(context, R.drawable.sky_fa05_tn)).a(this.e.e().contains(a2.b())).a(a7).a(), a(context, a2.b())));
//        a((List<TreeNode<FeatureItem>>) arrayList, (Set<String>) this.e.e());
//        return new TreeNode<>(FeatureItem.a().a("sky").b(context.getString(R.string.sky)).b(Integer.valueOf(R.drawable.ic_sky)).a(ToolbarItemStyle.ICON).a((FeatureItem.SelectedChildProvider) new FeatureItem.SelectedChildProvider() {
//            public String a(SessionState sessionState) {
//                return sessionState.g().e().c("sky_none");
//            }
//        }).a(), arrayList);
//    }
//
//    public final TreeNode<FeatureItem> b(Context context) {
//        return new TreeNode<>(FeatureItem.a().a("adjust").b(context.getString(R.string.adjust)).b(Integer.valueOf(R.drawable.ic_adjust)).a(ToolbarItemStyle.ICON).a(true).a(), a(context));
//    }
//
//    public final FeatureItem.FeatureItemSlider f(final Context context) {
//        return FeatureItem.FeatureItemSlider.a().b(0.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueProvider) C0137lf.a).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                ArrayList arrayList = new ArrayList(sessionState.e().b());
//                OverlayItemModel overlayItemModel = (OverlayItemModel) arrayList.get(arrayList.size() - 1);
//                arrayList.remove(overlayItemModel);
//                arrayList.add(overlayItemModel.f().b(f).a());
//                SessionState.Builder h = sessionState.h();
//                h.e().a(arrayList);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                ImmutableList<OverlayItemModel> b2 = sessionState.e().b();
//                return SessionStepCaption.a(context.getString(R.string.caption_element_opacity, new Object[]{Integer.valueOf(FeatureItemsRepository.b(b2.get(b2.size() - 1).d()))}));
//            }
//        }).a();
//    }
//
//    public final FeatureItem.FeatureItemSlider j(final Context context) {
//        return FeatureItem.FeatureItemSlider.a().b(0.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueProvider) C0103gf.a).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                ArrayList arrayList = new ArrayList(sessionState.f().b());
//                OverlayItemModel overlayItemModel = (OverlayItemModel) arrayList.get(arrayList.size() - 1);
//                arrayList.remove(overlayItemModel);
//                arrayList.add(overlayItemModel.f().b(f).a());
//                SessionState.Builder h = sessionState.h();
//                h.f().a(arrayList);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                ImmutableList<OverlayItemModel> b2 = sessionState.f().b();
//                return SessionStepCaption.a(context.getString(R.string.caption_overlay_opacity, new Object[]{Integer.valueOf(FeatureItemsRepository.b(b2.get(b2.size() - 1).d()))}));
//            }
//        }).a();
//    }
//
//    public final TreeNode<FeatureItem> e(Context context) {
//        AnonymousClass16 r0 = new FeatureItem.ItemClickedHandler() {
//            public Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources) {
//                SessionState.Builder h = sessionState.h();
//                CameraFxModel.Builder d = h.d();
//                if (str.equals("camera_fx_none")) {
//                    str = null;
//                }
//                d.a(str);
//                return Single.a(Optional.of(SessionStep.a().a(h.c()).a(SessionStepCaption.a(resources.getString(R.string.camera_fx))).a()));
//            }
//        };
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_none").b(context.getString(R.string.none_item_title)).a(UriUtils.a(context, R.drawable.none)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(FeatureItemsPackInfo.a().a((int) R.color.pnx_gray1).a()).a(), (List) null));
//        FeatureItemsPackInfo a2 = FeatureItemsPackInfo.a().a(context.getString(R.string.essential)).a((int) R.color.pnx_main_orange).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_01").b(context.getString(R.string.camera_fx_01_title)).a(UriUtils.a(context, R.drawable.fx01_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(a2).a(this.e.b().contains("camera_fx_01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_02").b(context.getString(R.string.camera_fx_02_title)).a(UriUtils.a(context, R.drawable.fx02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(a2).a(this.e.b().contains("camera_fx_02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_03").b(context.getString(R.string.camera_fx_03_title)).a(UriUtils.a(context, R.drawable.fx03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(a2).a(this.e.b().contains("camera_fx_03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_04").b(context.getString(R.string.camera_fx_04_title)).a(UriUtils.a(context, R.drawable.fx04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(a2).a(this.e.b().contains("camera_fx_04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_05").b(context.getString(R.string.camera_fx_05_title)).a(UriUtils.a(context, R.drawable.fx05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(a2).a(this.e.b().contains("camera_fx_05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("camera_fx_06").b(context.getString(R.string.camera_fx_06_title)).a(UriUtils.a(context, R.drawable.fx06_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a((FeatureItem.ItemClickedHandler) r0).a(a2).a(this.e.b().contains("camera_fx_06")).a(), (List) null));
//        a((List<TreeNode<FeatureItem>>) arrayList, (Set<String>) this.e.b());
//        return new TreeNode<>(FeatureItem.a().a("camera_fx").b(context.getString(R.string.camera_fx)).b(Integer.valueOf(R.drawable.ic_camera_fx)).a(ToolbarItemStyle.ICON).a((FeatureItem.SelectedChildProvider) new FeatureItem.SelectedChildProvider() {
//            public String a(SessionState sessionState) {
//                return sessionState.d().b().c("camera_fx_none");
//            }
//        }).a(), arrayList);
//    }
//
//    public final void a(Map<RemoteFeatureType, List<PackDescriptor>> map) {
//        this.d.a(new TreeNode(FeatureItem.a().a("pixaloop").b("").a(ToolbarItemStyle.ICON).a(), a(this.g, map)));
//    }
//
//    public final FeatureItem.ItemClickedHandler d() {
//        return new FeatureItem.ItemClickedHandler() {
//            public Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources) {
//                SessionState.Builder h = sessionState.h();
//                if ("overlay_name_none".equals(str)) {
//                    h.f().a(Collections.emptyList());
//                } else {
//                    ArrayList arrayList = new ArrayList(sessionState.f().b());
//                    if (arrayList.size() > 0) {
//                        if (((OverlayItemModel) arrayList.get(arrayList.size() - 1)).c().equals(str)) {
//                            return Single.a(Optional.empty());
//                        }
//                        arrayList.remove(arrayList.size() - 1);
//                    }
//                    h.f().a(Collections.singletonList(OverlayItemModel.a().a(str).a()));
//                }
//                return Single.a(Optional.of(SessionStep.a().a(h.c()).a(SessionStepCaption.a(resources.getString(R.string.overlay))).a()));
//            }
//        };
//    }
//
//    public final List<TreeNode<FeatureItem>> a(Context context, Map<RemoteFeatureType, List<PackDescriptor>> map) {
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(d(context));
//        arrayList.add(k(context));
//        arrayList.add(g(context));
//        arrayList.add(a(context, a(map, RemoteFeatureType.OVERLAY)));
//        arrayList.add(e(context));
//        arrayList.add(b(context));
//        return arrayList;
//    }
//
//    public static final int b(float f2) {
//        return Math.round(f2 * 100.0f);
//    }
//
//    public final void a(List<TreeNode<FeatureItem>> list, Set<String> set) {
//        FeatureItemsPackInfo featureItemsPackInfo = null;
//        boolean z = false;
//        for (TreeNode next : list) {
//            FeatureItemsPackInfo h = ((FeatureItem) next.b()).h();
//            Preconditions.a(h);
//            FeatureItemsPackInfo featureItemsPackInfo2 = h;
//            boolean z2 = true;
//            if (featureItemsPackInfo2.equals(featureItemsPackInfo)) {
//                Preconditions.a(z == ((FeatureItem) next.b()).m());
//            } else {
//                z = ((FeatureItem) next.b()).m();
//                featureItemsPackInfo = featureItemsPackInfo2;
//            }
//            if (((FeatureItem) next.b()).m() != set.contains(((FeatureItem) next.b()).f())) {
//                z2 = false;
//            }
//            Preconditions.a(z2);
//        }
//    }
//
//    public final List<TreeNode<FeatureItem>> a(final Context context, String str) {
//        ArrayList arrayList = new ArrayList();
//        FeatureItem.Builder a2 = FeatureItem.a();
//        arrayList.add(new TreeNode(a2.a(str + "_sky_haze").b(context.getString(R.string.sky_horizon)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(0.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.g().c(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_sky_haze, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.g().d()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) C0123jf.a).a()).a(), (List) null));
//        FeatureItem.Builder a3 = FeatureItem.a();
//        arrayList.add(new TreeNode(a3.a(str + "_sky_opacity").b(context.getString(R.string.sky_opacity)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(0.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.g().d(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_sky_opacity, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.g().f()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) C0089ef.a).a()).a(), (List) null));
//        FeatureItem.Builder a4 = FeatureItem.a();
//        arrayList.add(new TreeNode(a4.a(str + "_sky_speed").b(context.getString(R.string.sky_speed)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(-1.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.g().e(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_sky_speed, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.g().g()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) C0144mf.a).a()).a(), (List) null));
//        return arrayList;
//    }
//
//    public final TreeNode<FeatureItem> a(Context context, List<PackDescriptor> list) {
//        FeatureItem.ItemClickedHandler d2 = d();
//        FeatureItem.FeatureItemSlider j = j(context);
//        ImmutableSet<String> d3 = this.e.d();
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_name_none").b(context.getString(R.string.none_item_title)).a(UriUtils.a(context, R.drawable.none)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(FeatureItemsPackInfo.a().a((int) R.color.pnx_gray1).a()).a(), (List) null));
//        List<PackDescriptor> a2 = a(PackDescriptor.RelativePosition.BEFORE_ESSENTIAL_PACK, list);
//        arrayList.addAll(a(j, 0, a2));
//        int size = a2.size() + 0;
//        FeatureItemsPackInfo a3 = FeatureItemsPackInfo.a().a(context.getString(R.string.essential)).a(a(size)).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es01").b(context.getString(R.string.overlay_es01_title)).a(UriUtils.a(context, R.drawable.overlay_es09_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es02").b(context.getString(R.string.overlay_es02_title)).a(UriUtils.a(context, R.drawable.overlay_es08_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es03").b(context.getString(R.string.overlay_es03_title)).a(UriUtils.a(context, R.drawable.overlay_sk05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es04").b(context.getString(R.string.overlay_es04_title)).a(UriUtils.a(context, R.drawable.overlay_es05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es05").b(context.getString(R.string.overlay_es05_title)).a(UriUtils.a(context, R.drawable.overlay_es04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es06").b(context.getString(R.string.overlay_es06_title)).a(UriUtils.a(context, R.drawable.overlay_es02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es06")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es07").b(context.getString(R.string.overlay_es07_title)).a(UriUtils.a(context, R.drawable.overlay_es15_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es07")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_es08").b(context.getString(R.string.overlay_es08_title)).a(UriUtils.a(context, R.drawable.overlay_es06_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a3).a(d3.contains("overlay_es08")).a(), (List) null));
//        int i = size + 1;
//        List<PackDescriptor> a4 = a(PackDescriptor.RelativePosition.AFTER_ESSENTIAL_PACK, list);
//        arrayList.addAll(a(j, i, a4));
//        int size2 = i + a4.size();
//        List<PackDescriptor> a5 = a(PackDescriptor.RelativePosition.BEFORE_LOCAL_PACKS, list);
//        arrayList.addAll(a(j, size2, a5));
//        int size3 = size2 + a5.size();
//        FeatureItemsPackInfo a6 = FeatureItemsPackInfo.a().a(context.getString(R.string.overlay_graceful)).a(a(size3)).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc01").b(context.getString(R.string.overlay_gc01_title)).a(UriUtils.a(context, R.drawable.overlay_gc01_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc02").b(context.getString(R.string.overlay_gc02_title)).a(UriUtils.a(context, R.drawable.overlay_gc02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc03").b(context.getString(R.string.overlay_gc03_title)).a(UriUtils.a(context, R.drawable.overlay_gc03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc04").b(context.getString(R.string.overlay_gc04_title)).a(UriUtils.a(context, R.drawable.overlay_gc04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc05").b(context.getString(R.string.overlay_gc05_title)).a(UriUtils.a(context, R.drawable.overlay_gc05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc06").b(context.getString(R.string.overlay_gc06_title)).a(UriUtils.a(context, R.drawable.overlay_gc06_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc06")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_gc07").b(context.getString(R.string.overlay_gc07_title)).a(UriUtils.a(context, R.drawable.overlay_es03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a6).a(d3.contains("overlay_gc07")).a(), (List) null));
//        int i2 = size3 + 1;
//        FeatureItemsPackInfo a7 = FeatureItemsPackInfo.a().a(context.getString(R.string.overlay_sparkle)).a(a(i2)).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp01").b(context.getString(R.string.overlay_sp01_title)).a(UriUtils.a(context, R.drawable.overlay_sp01_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp02").b(context.getString(R.string.overlay_sp02_title)).a(UriUtils.a(context, R.drawable.overlay_sp02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp03").b(context.getString(R.string.overlay_sp03_title)).a(UriUtils.a(context, R.drawable.overlay_sp03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp04").b(context.getString(R.string.overlay_sp04_title)).a(UriUtils.a(context, R.drawable.overlay_sp04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp05").b(context.getString(R.string.overlay_sp05_title)).a(UriUtils.a(context, R.drawable.overlay_sp05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp06").b(context.getString(R.string.overlay_sp06_title)).a(UriUtils.a(context, R.drawable.overlay_sp06_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp06")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp07").b(context.getString(R.string.overlay_sp07_title)).a(UriUtils.a(context, R.drawable.overlay_sp07_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp07")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_sp08").b(context.getString(R.string.overlay_sp08_title)).a(UriUtils.a(context, R.drawable.overlay_es11_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a7).a(d3.contains("overlay_sp08")).a(), (List) null));
//        int i3 = i2 + 1;
//        FeatureItemsPackInfo a8 = FeatureItemsPackInfo.a().a(context.getString(R.string.overlay_light_leak)).a(a(i3)).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf01").b(context.getString(R.string.overlay_lf01_title)).a(UriUtils.a(context, R.drawable.overlay_lf01_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf02").b(context.getString(R.string.overlay_lf02_title)).a(UriUtils.a(context, R.drawable.overlay_lf02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf03").b(context.getString(R.string.overlay_lf03_title)).a(UriUtils.a(context, R.drawable.overlay_lf03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf04").b(context.getString(R.string.overlay_lf04_title)).a(UriUtils.a(context, R.drawable.overlay_lf04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf05").b(context.getString(R.string.overlay_lf05_title)).a(UriUtils.a(context, R.drawable.overlay_lf05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf06").b(context.getString(R.string.overlay_lf06_title)).a(UriUtils.a(context, R.drawable.overlay_es13_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf06")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_lf07").b(context.getString(R.string.overlay_lf07_title)).a(UriUtils.a(context, R.drawable.overlay_es12_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a8).a(d3.contains("overlay_lf07")).a(), (List) null));
//        int i4 = i3 + 1;
//        FeatureItemsPackInfo a9 = FeatureItemsPackInfo.a().a(context.getString(R.string.overlay_weather)).a(a(i4)).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt01").b(context.getString(R.string.overlay_wt01_title)).a(UriUtils.a(context, R.drawable.overlay_wt01_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt02").b(context.getString(R.string.overlay_wt02_title)).a(UriUtils.a(context, R.drawable.overlay_wt02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt03").b(context.getString(R.string.overlay_wt03_title)).a(UriUtils.a(context, R.drawable.overlay_wt03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt04").b(context.getString(R.string.overlay_wt04_title)).a(UriUtils.a(context, R.drawable.overlay_wt04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt05").b(context.getString(R.string.overlay_wt05_title)).a(UriUtils.a(context, R.drawable.overlay_wt05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt06").b(context.getString(R.string.overlay_wt06_title)).a(UriUtils.a(context, R.drawable.overlay_wt06_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt06")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt07").b(context.getString(R.string.overlay_wt07_title)).a(UriUtils.a(context, R.drawable.overlay_wt07_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt07")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_wt08").b(context.getString(R.string.overlay_wt08_title)).a(UriUtils.a(context, R.drawable.overlay_wt08_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a9).a(d3.contains("overlay_wt08")).a(), (List) null));
//        int i5 = i4 + 1;
//        FeatureItemsPackInfo a10 = FeatureItemsPackInfo.a().a(context.getString(R.string.overlay_party)).a(a(i5)).a();
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_st01").b(context.getString(R.string.overlay_st01_title)).a(UriUtils.a(context, R.drawable.overlay_st01_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a10).a(d3.contains("overlay_st01")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_st02").b(context.getString(R.string.overlay_st02_title)).a(UriUtils.a(context, R.drawable.overlay_st02_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a10).a(d3.contains("overlay_st02")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_st03").b(context.getString(R.string.overlay_st03_title)).a(UriUtils.a(context, R.drawable.overlay_st03_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a10).a(d3.contains("overlay_st03")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_st04").b(context.getString(R.string.overlay_st04_title)).a(UriUtils.a(context, R.drawable.overlay_st04_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a10).a(d3.contains("overlay_st04")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_st05").b(context.getString(R.string.overlay_st05_title)).a(UriUtils.a(context, R.drawable.overlay_st05_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a10).a(d3.contains("overlay_st05")).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("overlay_st06").b(context.getString(R.string.overlay_st06_title)).a(UriUtils.a(context, R.drawable.overlay_st06_tn)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(d2).a(j).a(a10).a(d3.contains("overlay_st06")).a(), (List) null));
//        arrayList.addAll(a(j, i5 + 1, a(PackDescriptor.RelativePosition.AFTER_LOCAL_PACKS, list)));
//        a((List<TreeNode<FeatureItem>>) arrayList, (Set<String>) this.e.d());
//        return new TreeNode<>(FeatureItem.a().a("overlay").b(context.getString(R.string.overlay)).b(Integer.valueOf(R.drawable.ic_overlays)).a(ToolbarItemStyle.ICON).a((FeatureItem.SelectedChildProvider) new FeatureItem.SelectedChildProvider() {
//            public String a(SessionState sessionState) {
//                ImmutableList<OverlayItemModel> b = sessionState.f().b();
//                return b.size() > 0 ? b.get(b.size() - 1).c() : "overlay_name_none";
//            }
//        }).a(), arrayList);
//    }
//
//    public final FeatureItem.SelectedChildProvider c() {
//        return new FeatureItem.SelectedChildProvider() {
//            @Nullable
//            public String a(SessionState sessionState) {
//                return (String) FeatureItemsRepository.b.get(sessionState.c().g());
//            }
//        };
//    }
//
//    public final List<PackDescriptor> a(PackDescriptor.RelativePosition relativePosition, List<PackDescriptor> list) {
//        ArrayList arrayList = new ArrayList();
//        for (PackDescriptor next : list) {
//            if (next.relativePosition.equals(relativePosition)) {
//                arrayList.add(next);
//            }
//        }
//        return arrayList;
//    }
//
//    public final List<TreeNode<FeatureItem>> a(FeatureItem.FeatureItemSlider featureItemSlider, int i, List<PackDescriptor> list) {
//        ArrayList arrayList = new ArrayList();
//        FeatureItem.ItemClickedHandler a2 = a(list);
//        for (PackDescriptor next : list) {
//            FeatureItemsPackInfo a3 = FeatureItemsPackInfo.a().a(next.name).a(a(i)).a();
//            int i2 = 1;
//            for (AssetDescriptor next2 : next.assetDescriptors) {
//                arrayList.add(new TreeNode(FeatureItem.a().a(next2.id).b(a(next, i2)).a(this.f.a(next2.thumbnailUrl)).a(ToolbarItemStyle.PACK).a(FeatureItem.ActivationPolicy.SELECT_NO_DESELECT).a(a2).b(true).a(featureItemSlider).a(a3).a(false).a(), (List) null));
//                i2++;
//            }
//            i++;
//        }
//        return arrayList;
//    }
//
//    public final String a(PackDescriptor packDescriptor, int i) {
//        return String.format("%s%02d", new Object[]{packDescriptor.shortName, Integer.valueOf(i)});
//    }
//
//    @ColorRes
//    public final int a(int i) {
//        int[] iArr = a;
//        return iArr[i % iArr.length];
//    }
//
//    public final FeatureItem.ItemClickedHandler a(final List<PackDescriptor> list) {
//        return new FeatureItem.ItemClickedHandler() {
//            public Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources) {
//                SessionState.Builder h = sessionState.h();
//                ArrayList arrayList = new ArrayList(sessionState.f().b());
//                if (arrayList.size() > 0) {
//                    if (((OverlayItemModel) arrayList.get(arrayList.size() - 1)).c().equals(str)) {
//                        return Single.a(Optional.empty());
//                    }
//                    arrayList.remove(arrayList.size() - 1);
//                }
//                return FeatureItemsRepository.this.f.a(str, (List<PackDescriptor>) list).a(Single.a(new C0075cf(h, str, resources)));
//            }
//
//            public static /* synthetic */ void a(SessionState.Builder builder, String str, Resources resources, SingleEmitter singleEmitter) {
//                builder.f().a(Collections.singletonList(OverlayItemModel.a().a(str).a()));
//                if (!singleEmitter.b()) {
//                    singleEmitter.c(Optional.of(SessionStep.a().a(builder.c()).a(SessionStepCaption.a(resources.getString(R.string.overlay))).a()));
//                }
//            }
//        };
//    }
//
//    public static FeatureItem.ItemClickedHandler a(final String str) {
//        return new FeatureItem.ItemClickedHandler() {
//            public Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources) {
//                SessionState.Builder h = sessionState.h();
//                if (str.equals(str)) {
//                    h.e().a(Collections.emptyList());
//                } else {
//                    ImmutableList<OverlayItemModel> b = sessionState.e().b();
//                    if (b.size() > 0 && b.get(b.size() - 1).c().equals(str)) {
//                        return Single.a(Optional.empty());
//                    }
//                    h.e().a(Collections.singletonList(OverlayItemModel.a().a(str).a()));
//                }
//                return Single.a(Optional.of(SessionStep.a().a(h.c()).a(SessionStepCaption.a(resources.getString(R.string.caption_element))).a()));
//            }
//        };
//    }
//
//    public final List<TreeNode<FeatureItem>> a(final Context context) {
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(new TreeNode(FeatureItem.a().a("adjust_brightness").b(context.getString(R.string.adjust_brightness)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(-1.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.a().a(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_adjust_brightness, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.b().b()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) C0110hf.a).a()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("adjust_contrast").b(context.getString(R.string.adjust_contrast)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(-1.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.a().b(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_adjust_contrast, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.b().c()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) C0130kf.a).a()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("adjust_saturation").b(context.getString(R.string.adjust_saturation)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(-1.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.a().c(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_adjust_saturation, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.b().d()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) C0096ff.a).a()).a(), (List) null));
//        arrayList.add(new TreeNode(FeatureItem.a().a("adjust_temperature").b(context.getString(R.string.adjust_temperature)).a(ToolbarItemStyle.NUMBER).a(FeatureItem.ActivationPolicy.SELECT_AND_DESELECT).a(FeatureItem.FeatureItemSlider.a().b(-1.0f).a(1.0f).a((FeatureItem.FeatureItemSlider.ValueUpdater) new FeatureItem.FeatureItemSlider.ValueUpdater() {
//            @Nullable
//            public SessionState a(float f, SessionState sessionState) {
//                SessionState.Builder h = sessionState.h();
//                h.a().d(f);
//                return h.c();
//            }
//
//            public SessionStepCaption a(SessionState sessionState) {
//                return SessionStepCaption.a(context.getString(R.string.caption_adjust_temperature, new Object[]{Integer.valueOf(FeatureItemsRepository.b(sessionState.b().e()))}));
//            }
//        }).a((FeatureItem.FeatureItemSlider.ValueProvider) Cif.a).a()).a(), (List) null));
//        return arrayList;
//    }
//
//    public static List<PackDescriptor> a(Map<RemoteFeatureType, List<PackDescriptor>> map, RemoteFeatureType remoteFeatureType) {
//        return map.containsKey(remoteFeatureType) ? map.get(remoteFeatureType) : new ArrayList();
//    }
//}
