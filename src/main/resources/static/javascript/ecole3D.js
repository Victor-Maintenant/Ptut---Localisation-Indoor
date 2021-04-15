import * as THREE from 'https://unpkg.com/three@0.127.0/build/three.module.js';
import { OrbitControls } from 'https://unpkg.com/three@0.127.0/examples/jsm/controls/OrbitControls.js';
import { GLTFLoader } from 'https://unpkg.com/three@0.127.0/examples/jsm/loaders/GLTFLoader.js'

/**
 *  Textures
 */
const textureLoader = new THREE.TextureLoader()
 const cubeTextureLoader = new THREE.CubeTextureLoader()

 const matcapTexture = textureLoader.load('../static/3Dmodels/textures/fondEcole.png')


/**
 * Base
 */


// Canvas
const canvas = document.querySelector('canvas.webgl')

// Scene
const scene = new THREE.Scene()
scene.background = matcapTexture


/**
 *  Models
 */
const gltfLoader = new GLTFLoader()

gltfLoader.load(
    '../static/3Dmodels/isis.gltf',
    (gltf) =>
    {
        console.log('success')
        console.log(gltf)
        while(gltf.scene.children.length)
        {
            scene.add(gltf.scene.children[0])
        }
    }
)



/**
 * Lights
 */
const ambientLight = new THREE.AmbientLight(0xffffff, 1)
scene.add(ambientLight)



const directionalLight = new THREE.DirectionalLight(0xffffff, 0.5)
directionalLight.position.set(50, 30, 50)
scene.add(directionalLight)
const directionalLightHelper = new THREE.DirectionalLightHelper(directionalLight, 0.2)
directionalLightHelper.visible = false
scene.add(directionalLightHelper)
const directionalLight2 = new THREE.DirectionalLight(0xffffff, 1.5)
directionalLight2.position.set(-50, 30, -50)
scene.add(directionalLight2)
const directionalLightHelper2 = new THREE.DirectionalLightHelper(directionalLight2, 0.2)
directionalLightHelper2.visible = false
scene.add(directionalLightHelper2)


/**
 * Sizes
 */
const sizes = {
    width: window.innerWidth,
    height: window.innerHeight
}

window.addEventListener('resize', () =>
{
    // Update sizes
    sizes.width = window.innerWidth
    sizes.height = window.innerHeight

    // Update camera
    camera.aspect = sizes.width / sizes.height
    camera.updateProjectionMatrix()

    // Update renderer
    renderer.setSize(sizes.width, sizes.height*0.7)
    renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2))
})

/**
 * Camera
 */
// Base camera
const camera = new THREE.PerspectiveCamera(75, sizes.width / sizes.height, 0.1, 1000)
camera.position.set(70, 30, 110)
scene.add(camera)

// Controls
const controls = new OrbitControls(camera, canvas)
controls.target.set(0, 0.75, 0)
controls.autoRotate = true
controls.autoRotateSpeed = 0.6
controls.enableDamping = true
controls.maxPolarAngle = Math.PI * 0.5
controls.maxDistance = 150
controls.addEventListener( 'change', light_update );

function light_update()
{
    directionalLight.position.copy( camera.position );
}

/**
 * Renderer
 */
const renderer = new THREE.WebGLRenderer({
    canvas: canvas,
})
renderer.setSize(sizes.width, sizes.height*0.7)
renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2))

/**
 * Animate
 */
const clock = new THREE.Clock()
let previousTime = 0

const tick = () =>
{
    const elapsedTime = clock.getElapsedTime()
    const deltaTime = elapsedTime - previousTime
    previousTime = elapsedTime


    // Update controls
    controls.update()

    // Render
    renderer.render(scene, camera)

    // Call tick again on the next frame
    window.requestAnimationFrame(tick)
}

tick()